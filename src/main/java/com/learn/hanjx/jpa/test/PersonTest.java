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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		Long time1 = System.currentTimeMillis();
		for(int i = 1;i<Integer.MAX_VALUE/2000;i++){
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			Person p = new Person();
			p.setName("wang"+System.currentTimeMillis());
			p.setBirthDay(new Date());
			IDCard ic = new IDCard(""+System.currentTimeMillis());
			p.setIdcard(ic);
			em.persist(p);
			em.getTransaction().commit();
			em.close();
		}

		factory.close();
		System.out.print((System.currentTimeMillis()-time1)+"ms");
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
