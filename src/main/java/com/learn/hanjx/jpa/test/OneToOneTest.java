package com.learn.hanjx.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.learn.hanjx.jpa.bean.IDCard;
import com.learn.hanjx.jpa.bean.Person;

public class OneToOneTest {
	@Test
	public void save(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = new Person("dfdfd");
		IDCard idCard = new IDCard("122233333333");
		idCard.setPerson(person);
		person.setIdcard(idCard);
		em.persist(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void deletePerson(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, 1);
		em.remove(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
