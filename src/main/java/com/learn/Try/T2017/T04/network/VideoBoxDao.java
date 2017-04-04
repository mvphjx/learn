package com.learn.Try.T2017.T04.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.learn.hanjx.jpa.bean.MyLog;
import com.learn.hanjx.jpa.bean.VideoBox;
import com.learn.hanjx.util.io.FileUtils;

public class VideoBoxDao {
	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUNIT");
	public  static void save(VideoBox model){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(model);
		em.getTransaction().commit();
		em.close();
		//System.out.println(model.getTitle());
	}
	public  static VideoBox get(VideoBox model){
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select o from VideoBox o where o.viewkey=?1");
		query.setParameter(1, model.getViewkey());
		VideoBox p = null;
		if(query.getResultList().size()>0){
			p = (VideoBox)query.getResultList().get(0);
		}
		em.close();
		return p;
		//System.out.println(model.getTitle());
	}
	public  static void delete(int id){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		VideoBox model = em.find(VideoBox.class, id);
		em.remove(model);
		em.getTransaction().commit();
		em.close();
		//System.out.println(model.getTitle());
	}
	public  static void distinct(){
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("SELECT t.viewkey from VideoBox t group by t.viewkey HAVING count(t.viewkey) >1");
		List list = query.getResultList();
		em.close();
		for(Object obj :list){
			em = factory.createEntityManager();
			Query query2 = em.createQuery("SELECT t from VideoBox t where t.viewkey= ?1");
			query2.setParameter(1,obj);
			List list2 = query2.getResultList();
			em.close();
			int i =0;
			for(Object obj2 :list2){
				if(i==0){
					i++;
					continue;
				}
				em = factory.createEntityManager();
				obj2= em.find(VideoBox.class, ((VideoBox)obj2).getId());
				em.remove(obj2);
				em.close();
			}
		}
	}
	public  static void savelog(MyLog model){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(model);
		em.getTransaction().commit();
		em.close();
		//System.out.println(model.getTitle());
	}
	public static void main(String[] args) throws IOException {
		savelog();
	}
	//记录分析日志
	private static void savelog() throws IOException {
		BufferedReader bufferedReader=FileUtils.readTxtFileReader("D:/temp/test.txt");
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			MyLog model = new MyLog();
			model.setTitle(lineTxt.substring(lineTxt.indexOf("&page=")+"&page=".length()));
			model.setType(lineTxt.startsWith("get page:")?"back":"call");
			//System.out.println(model);
			savelog(model);
			
		}
		bufferedReader.close();
	}
	
}
