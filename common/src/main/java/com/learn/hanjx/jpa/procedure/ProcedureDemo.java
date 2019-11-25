package com.learn.hanjx.jpa.procedure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

public class ProcedureDemo {
  public static void main(String[] args) {
	  /*
	   CREATE OR REPLACE PROCEDURE batchUpdatePerson(namepa varchar2)
		IS
		BEGIN
  		--dbms_output.put_line(namep);
  		UPDATE PERSON set name = namepa;
		END;
		
		begin
		batchupdateperson('11LMM');
		end;
	   */
	  	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		Session session = null;
		if(em.getDelegate() instanceof org.hibernate.internal.SessionImpl){
			session = (Session) em.getDelegate();						
		}
		assert session==null;
		String   procedure   =   "{call   batchUpdatePerson(?)   }";
		Query query = em.createNativeQuery(procedure);
		query.setParameter(1, "new name:"+System.currentTimeMillis());
		//String result = query.getSingleResult().toString();
		//Could not extract result set metadata
		query.getSingleResult();
		
}
}
