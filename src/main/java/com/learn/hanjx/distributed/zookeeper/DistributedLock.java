package com.learn.hanjx.distributed.zookeeper;
/**
 * 分布式系统中经常需要协调多进程，多个jvm，或者多台机器之间的同步问题，
 * 得益于zookeeper，实现了一个分布式的共享锁，
 * 方便在多台服务器之间竞争资源时，来协调各系统之间的协作和同步。
 * 
 * 一个是保持独占，
 * 通常的做法是把zk上的一个znode看作是一把锁，通过create znode的方式来实现。所有客户端都去创建 /distribute_lock 节点，最终成功创建的那个客户端也即拥有了这把锁。
 * 
 * 另一个是控制时序（根据节点生成的序号，排序）
 * 创建临时有序节点（这个可以通过节点的属性控制：CreateMode.EPHEMERAL_SEQUENTIAL来指定），有序执行
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
 

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
 
public class DistributedLock implements Lock, Watcher{
    private ZooKeeper zk;
    private String root = "/locks";//根
    private String lockName;//竞争资源的标志
    private String waitNode;//等待前一个锁
    private String myZnode;//当前锁
    private CountDownLatch latch;//计数器
    private int sessionTimeout = 7000;//
    private List<Exception> exception = new ArrayList<Exception>();
     
    /**
     * 创建分布式锁,使用前请确认config配置的zookeeper服务可用
     * @param config 127.0.0.1:2181
     * @param lockName 竞争资源标志,lockName中不能包含单词lock
     * @throws IOException 
     * @throws InterruptedException 
     * @throws KeeperException 
     */
    public DistributedLock(String config, String lockName) {
    	this.lockName = lockName;
    	try {
    		zk = new ZooKeeper(config, sessionTimeout, null);
            Stat stat = zk.exists(root, false);
            if(stat == null){
                // 创建根节点
                zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
            }
    	 } catch (IOException e) {
        exception.add(e);
    	 } catch (KeeperException e) {
        exception.add(e);
    	 } catch (InterruptedException e) {
        exception.add(e);
    	 }
    }
    /**
     * zookeeper节点的监视器
     */
    public void process(WatchedEvent event) {
        if(this.latch != null) {  
            this.latch.countDown();  
        }
    }
     
    public void lock() {
        if(exception.size() > 0){
            throw new LockException(exception.get(0));
        }
        try {
            if(this.tryLock()){
                System.out.println("Thread " + Thread.currentThread().getId() + " " +myZnode + "  [get lock]");
                return;
            }
            else{
                waitForLock(waitNode, sessionTimeout);//等待锁
            }
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        } 
    }
 
    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if(lockName.contains(splitStr))
                throw new LockException("lockName can not contains \\u000B");
            //创建临时子节点
            myZnode = zk.create(root + "/" + lockName + splitStr, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(myZnode + " [created] ");
            //取出所有子节点
            List<String> subNodes = zk.getChildren(root, false);
            //取出所有lockName的锁
            List<String> lockObjNodes = new ArrayList<String>();
            for (String node : subNodes) {
                String _node = node.split(splitStr)[0];
                if(_node.equals(lockName)){
                    lockObjNodes.add(node);
                }
            }
            Collections.sort(lockObjNodes);
            System.out.println("当前节点："+myZnode + "；最小节点：" + lockObjNodes.get(0));
            if(myZnode.equals(root+"/"+lockObjNodes.get(0))){
                //如果是最小的节点,则表示取得锁
            	System.out.println("取得锁的节点:"+myZnode);
                return true;
            }
            //如果不是最小的节点，找到比自己小1的节点
            String subMyZnode = myZnode.substring(myZnode.lastIndexOf("/") + 1);
            waitNode = lockObjNodes.get(Collections.binarySearch(lockObjNodes, subMyZnode) - 1);
            System.out.println("需要等待:"+waitNode);
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }
        return false;
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        try {
            if(this.tryLock()){
                return true;
            }
            return waitForLock(waitNode,time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    private boolean waitForLock(String lower, long waitTime) throws InterruptedException, KeeperException {
        Stat stat = zk.exists(root + "/" + lower,true);
        //判断比自己小一个数的节点是否存在,如果不存在则无需等待锁,同时注册监听
        if(stat != null){
            System.out.println("Thread " + Thread.currentThread().getId() + " [waiting for] " + root + "/" + lower);
            this.latch = new CountDownLatch(1);
            this.latch.await(waitTime, TimeUnit.MILLISECONDS);
            //return waitForLock(lower,waitTime);            
        }
        this.latch = null;
        return true;
    }
 
    public void unlock() {
        try {
            System.out.println(myZnode+"  [unlock] ");
            zk.delete(myZnode,-1);
            myZnode = null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
 
    public void lockInterruptibly() throws InterruptedException {
        this.lock();
    }
 
    public Condition newCondition() {
        return null;
    }
     
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
    	ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, null);
		  System.out.println("=========创建节点===========");
		  if(zk.exists("/test", false) == null)
		  {
		    zk.create("/test", "znode1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		  }
		  System.out.println("=============查看节点是否安装成功===============");
		  System.out.println(new String(zk.getData("/test", false, null)));
	}
 
}