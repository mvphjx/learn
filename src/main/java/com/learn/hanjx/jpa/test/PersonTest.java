package com.learn.hanjx.jpa.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;

import com.learn.hanjx.jpa.bean.IDCard;
import com.learn.hanjx.jpa.bean.Person;

public class PersonTest {
	  @BeforeClass  
	  public static void setUpBeforeClass() throws Exception {  
	  }  
	    
	  @Test 
	  public void createTable(){  
	      //可以验证生成表是否正确  
	      EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");  
	      factory.close();  
	  }  
	@Test
	public void save(){

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person p = new Person();
		p.setName("wang");
		p.setBirthDay(new Date());
		IDCard ic = new IDCard("37");
		p.setIdcard(ic);
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	@Test
	public void saveMany(){
		int num = Integer.MAX_VALUE/2000000;
		/*
		 * 2000save
		 * 9168ms1972ms1108ms
		 * 20000save
		 * 621947ms20219ms11408ms
		 */
		num = 5000;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		/*
		 * 不清缓存 最慢  复用 EntityManager但是多个事务提交
		 * 清缓存 中等
		 */
		Long time1 = System.currentTimeMillis();
		Long time1_begin = 0L;
		Long time1_persist = 0L;
		Long time1_commit = 0L;

		EntityManager em = factory.createEntityManager();
		for(int i = 1;i<num;i++){
			
			Long time_mask1 = System.currentTimeMillis();
			em.getTransaction().begin();
			Long time_mask2 = System.currentTimeMillis();
			time1_begin += time_mask2-time_mask1;
			Person p = new Person();
			p.setName("wang"+System.currentTimeMillis());
			p.setBirthDay(new Date());
			IDCard ic = new IDCard(""+System.currentTimeMillis());
			p.setIdcard(ic);
			Long time_mask3 = System.currentTimeMillis();
			em.persist(p);
			Long time_mask4 = System.currentTimeMillis();
			time1_persist+=time_mask4-time_mask3;
			em.getTransaction().commit();
			Long time_mask5 = System.currentTimeMillis();
			time1_commit += time_mask5-time_mask4;
			//System.out.println(time1_commit);
			//em.clear();
		    /**
		     * 瞬态 持久态  游离态。。。。这个jpa上下文，会缓存持久态的对象。。。应该弄成  游离态
		     * 
		     *commit的时间,会多很多：怀疑,应该每次commit都会,检查缓存的持久态对象是否改变。
		     * 
		     * http://blog.csdn.net/zjkstone/article/details/7905462
		     * 
		     * Remove the given entity from the persistence context, causing
		     * a managed entity to become detached.  Unflushed changes made
		     * to the entity if any (including removal of the entity),
		     * will not be synchronized to the database.  Entities which
		     * previously referenced the detached entity will continue to
		     * reference it.
		     * @param entity  entity instance
		     * @throws IllegalArgumentException if the instance is not an
		     *         entity
		     * @since Java Persistence 2.0
		     */
			em.detach(p);
		}
		em.close();
		System.out.println((System.currentTimeMillis()-time1)+"ms");
		System.out.println("time1_begin:"+time1_begin+"ms  time1_persist:"+time1_persist+"ms  time1_commit:"+time1_commit+"ms");
		// new EntityManager
		Long time2_create = 0L;
		Long time2_begin = 0L;
		Long time2_persist = 0L;
		Long time2_commit = 0L;
		Long time2_close = 0L;
		Long time2 = System.currentTimeMillis();
		for(int i = 1;i<num;i++){
			Long time_mask1 = System.currentTimeMillis();
			EntityManager em1 = factory.createEntityManager();
			Long time_mask2 = System.currentTimeMillis();
			time2_create+= time_mask2-time_mask1;
			em1.getTransaction().begin();
			Long time_mask3 = System.currentTimeMillis();
			time2_begin+= time_mask3-time_mask2;
			Person p = new Person();
			p.setName("wang"+System.currentTimeMillis());
			p.setBirthDay(new Date());
			IDCard ic = new IDCard(""+System.currentTimeMillis());
			p.setIdcard(ic);
			Long time_mask4 = System.currentTimeMillis();
			em1.persist(p);
			Long time_mask5 = System.currentTimeMillis();
			time2_persist+= time_mask5-time_mask4;
			em1.getTransaction().commit();
			Long time_mask6 = System.currentTimeMillis();
			time2_commit+= time_mask6-time_mask5;
			em1.close();
			Long time_mask7 = System.currentTimeMillis();
			time2_close+= time_mask7-time_mask6;
		}
		System.out.println((System.currentTimeMillis()-time2)+"ms");
		System.out.println("time2_create:"+time2_create+"ms  time2_begin:"+time2_begin+"ms  time2_persist:"+time2_persist
				+"ms  time2_commit:"+time2_commit
				+"ms  time2_close:"+time2_close+"ms"
				);
		//最快  一个EntityManager 长事务提交
		Long time3 = System.currentTimeMillis();
		EntityManager em3 = factory.createEntityManager();
		em3.getTransaction().begin();
		for(int i = 1;i<num;i++){
			Person p = new Person();
			p.setName("wang"+System.currentTimeMillis());
			p.setBirthDay(new Date());
			IDCard ic = new IDCard(""+System.currentTimeMillis());
			p.setIdcard(ic);
			em3.persist(p);
		}
		em3.getTransaction().commit();
		em3.close();
		System.out.println((System.currentTimeMillis()-time3)+"ms");
		factory.close();
	}
	
	@Test
	public void get1(){
		try{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		Person p = em.find(Person.class, 1);
		System.out.println(p.getName());
		em.close();
		factory.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void get2(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		Person p = em.getReference(Person.class, 1);
		System.out.println(p.getName());
		em.close();
		factory.close();
	}
	
	@Test
	public void update1(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person p = em.find(Person.class, 1);
		p.setName("4444");
		em.merge(p);
		System.out.println(p.getName());
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	//@Test
	public void delete1(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person p = em.find(Person.class, 1);
		em.remove(p);
		System.out.println(p.getName());
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void query(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select o from Person o where o.id=?1");
		query.setParameter(1, 1);
		Person p = (Person)query.getResultList().get(0);
		System.out.println(p.toString());
		em.close();
		factory.close();
	}
	
	//@Test
	public void delete2(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("delete from Person o where o.id=?1");
		query.setParameter(1, 2);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void update2(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("update Person o set o.name=?1 where o.id=?2");
		query.setParameter(1, "uuuu");
		query.setParameter(2, 3);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
