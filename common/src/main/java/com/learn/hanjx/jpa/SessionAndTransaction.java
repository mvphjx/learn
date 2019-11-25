package com.learn.hanjx.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import com.learn.hanjx.jpa.bean.IDCard;
import com.learn.hanjx.jpa.bean.Person;

public class SessionAndTransaction {
	/**
	 *
	 *
	 * 持久化对象的生命周期
	 * 				瞬时态 		 持久态		  游离态
	 * session		不存在		  存在			   不存在
	 * database		不存在		  存在			   存在
	 *
	 *
	 *

   ① Serializable (串行化)：可避免脏读、不可重复读、幻读的发生。

　　② Repeatable read (可重复读)：可避免脏读、不可重复读的发生。

　　③ Read committed (读已提交)：可避免脏读的发生。

　　④ Read uncommitted (读未提交)：最低级别，任何情况都无法保证。


	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		Session session = null;
		if(em.getDelegate() instanceof org.hibernate.internal.SessionImpl){
			session = (Session) em.getDelegate();
		}
		assert session==null;
		em.getTransaction().begin();//开启事务
		Person person = new Person();//瞬时态
		person.setName("wang");
		person.setBirthDay(new Date());
		IDCard ic = new IDCard("37");
		person.setIdcard(ic);
		em.persist(person);
		session.flush();//持久态    （如果事务隔离级别为 读未提交  数据库可以看到  ）
		session.evict(person);//游离态
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
