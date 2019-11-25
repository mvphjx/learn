http://blog.csdn.net/liudezhicsdn/article/details/51124655
https://my.oschina.net/tinyframework/blog/192778

分布式锁是控制分布式系统之间同步访问共享资源的一种方式。
在分布式系统中，常常需要协调他们的动作。
如果不同的系统或是同一个系统的不同主机之间共享了一个或一组资源，
那么访问这些资源的时候，往往需要互斥来防止彼此干扰来保证一致性，在这种情况下，便需要使用到分布式锁。



这是一个简单分布式锁，主要通过RMI（远程调用）来实现锁控制




分布式锁
https://github.com/javamonkey/springdumpling