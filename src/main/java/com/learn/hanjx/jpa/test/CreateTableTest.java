package com.learn.hanjx.jpa.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class CreateTableTest {
		  @Test 
		  public void createTable(){  
		      //可以验证生成表是否正确  
		      EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");  
		      factory.close();  
		  }  
}
