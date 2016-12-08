package com.learn.hanjx.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.learn.hanjx.jpa.bean.Order;
import com.learn.hanjx.jpa.bean.OrderItem;

public class OneToManyTest {
	
	@Test
	public void save(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Order order = new Order();
		order.setAmount(34f);
		order.setId("999");
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setProductName("足球");
		orderItem1.setSellPrice(90f);
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setProductName("瑜伽球");
		orderItem2.setSellPrice(30f);
		order.addOrderItem(orderItem1);
		order.addOrderItem(orderItem2);
		em.persist(order);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
