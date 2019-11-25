package com.learn.hanjx.jpa.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.learn.hanjx.jpa.bean.Person;

public class PersonService {
	private  EntityManagerFactory factory;
	private  EntityManager em;
	{
		factory = Persistence.createEntityManagerFactory("JPAUNIT");
		em = factory.createEntityManager();
		
	}
	public Person query(Integer i){

		Query query = em.createQuery("select o from Person o where o.id=?1");
		query.setParameter(1, i);
		Person p = (Person)query.getResultList().get(0);
		System.out.println(p.toString());
		em.close();
		factory.close();
		return p;
	}
	

}
