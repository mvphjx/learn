package com.learn.hanjx.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.learn.hanjx.jpa.bean.AirLine;

public class AirLineTest {
	@Test
	public void save(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(new AirLine("pek","sha","北京飞上海"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
