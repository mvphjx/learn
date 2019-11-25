package com.learn.hanjx.jpa.procedure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

public class FunctionDemo {
  public static void main(String[] args) {
	  /*
		CREATE OR REPLACE  FUNCTION F_batchUpdatePerson(namepa NVARCHAR2)
   			RETURN NUMBER is
   			rows NUMBER;
		BEGIN
   			UPDATE PERSON set name = namepa;
   			rows := SQL%ROWCOUNT;
   			return(rows);
		END;

测试方式1  不能测试dml语句
select f_batchupdateperson('2222')  from  dual
测试方式2
BEGIN  
dbms_output.put_line(f_batchupdateperson('222'));  
END; 
	   */
	  EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		Session session = null;
		if(em.getDelegate() instanceof org.hibernate.internal.SessionImpl){
			session = (Session) em.getDelegate();						
		}
		assert session==null;
		//TODO
		Query query = em . createNamedQuery("f_batchupdateperson") ;
		query.setParameter(1, "f_batchupdateperson:"+System.currentTimeMillis());
		query.getSingleResult();
		
}
}
