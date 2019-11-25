package com.learn.hanjx.jpa.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.learn.hanjx.jpa.bean.Student;
import com.learn.hanjx.jpa.bean.Teacher;

public class ManyToManyTest {
	@Test
	public void save(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Teacher t = new Teacher("wang");
		Student s = new Student("xiao");
		
		//自己维护  关联表
		Set<Teacher> ss =new HashSet<Teacher>();
		ss.add(t);
		s.setTeachers(ss);
		
		
		em.persist(s);
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void buildTS(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		student.addTeacher(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void deleteTS(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		student.removeTeacher(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void deleteTeacher(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		Teacher teacher = em.getReference(Teacher.class, 1);
		student.removeTeacher(teacher);
		em.remove(teacher);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void deleteStudent(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.getReference(Student.class, 1);
		em.remove(student);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
