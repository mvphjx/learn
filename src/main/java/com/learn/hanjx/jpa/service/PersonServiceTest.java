package com.learn.hanjx.jpa.service;

import org.junit.Test;

public class PersonServiceTest {

	@Test
	public void testQuery() {
		PersonService ps = new PersonService();
		assert(ps==null);
		toString(ps.query(1));
	}
	
	
	
	private void toString(Object s){
		System.out.println(s.toString());
	}
	

}
