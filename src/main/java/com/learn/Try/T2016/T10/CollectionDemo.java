package com.learn.Try.T2016.T10;
/**
 * 集合简单操作
 * 增加删除
 * 遍历 
 * 排序（实现接口或者实现比较器）
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.junit.Test;

/*
 * 问题：某班30个学生的学号为20070301-20070330,
 * 全部选修了Java程序设计课程，给出所有同学的成绩（可用随机数产生，范围60-100），
 * 请编写程序将本班各位同学的成绩按照从高到低排序打印输出。
     要求：分别用List、Map、Set来实现，打印的信息包括学号、姓名和成绩。
 */
public class CollectionDemo {
	public static List list = null;
	public static void main(String[] args) {
		
	}
	@Test
	public void byList(){
		List<CourceAndStudent> list = new ArrayList<>();
		Random rand = new Random();
		for(int i=1;i<=30;i++){
			CourceAndStudent cs = new CourceAndStudent();
			cs.setId(20070300+i);
			cs.setName("name"+i);
			cs.setScore(rand.nextInt(40)+60);
			list.add(cs);
		}
		Collections.sort(list, new Comparator<CourceAndStudent>() {
			@Override
			public int compare(CourceAndStudent o1, CourceAndStudent o2) {
				if(o1!=null&&o2!=null){
					if(o1.score>o2.score)
						return 1;
					else
						return -1;
				}
				return 0;
			}
		});
		System.out.println("\n\nlist ");
		list.remove(10);//test
		for(CourceAndStudent cs :list){
			System.out.println(cs.id+"->"+cs.score);
		}
	}
	@Test
	public void bySet(){
		Set<CourceAndStudent> set = new HashSet<>();
		Random rand = new Random();
		for(int i=1;i<=30;i++){
			CourceAndStudent cs = new CourceAndStudent();
			cs.setId(20070300+i);
			cs.setName("name"+i);
			cs.setScore(rand.nextInt(40)+60);
			set.add(cs);
		}
		ArrayList<CourceAndStudent>li=new ArrayList<>(set);  
		Collections.sort(li, new Comparator<CourceAndStudent>() {
			@Override
			public int compare(CourceAndStudent o1, CourceAndStudent o2) {
				if(o1!=null&&o2!=null){
					if(o1.score>o2.score)
						return 1;
					else
						return -1;
				}
				return 0;
			}
		});
		System.out.println("\n\nset ");
		for(CourceAndStudent cs :li){
			System.out.println(cs.id+"->"+cs.score);
		}
	}
	@Test
	public void byMap(){
		Map<String,CourceAndStudent> map = new HashMap<>();
		Random rand = new Random();
		for(int i=1;i<=30;i++){
			CourceAndStudent cs = new CourceAndStudent();
			cs.setId(20070300+i);
			cs.setName("name"+i);
			cs.setScore(rand.nextInt(40)+60);
			map.put(String.valueOf(cs.id), cs);
		}
		Set<Entry<String, CourceAndStudent>> set=map.entrySet();
		ArrayList<Entry<String, CourceAndStudent>>list=new ArrayList<>(set);  
		Collections.sort(list, new Comparator<Entry<String, CourceAndStudent>>() {
			@Override
			public int compare(Entry<String, CourceAndStudent> o1,
					Entry<String, CourceAndStudent> o2) {
				if(o1!=null&&o2!=null){
					return o1.getValue().score>o2.getValue().score?1:-1;
				}
				return 0;
			}
		});
		System.out.println("\n\nmap ");
		for(Entry<String, CourceAndStudent> e :list){
			System.out.println(e.getValue().id+"->"+e.getValue().score);
		}
	}
	class CourceAndStudent {
		private long id;
		private String name;
		private String cource="java";
		private int score;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCource() {
			return cource;
		}
		public void setCource(String cource) {
			this.cource = cource;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		
	}

}
