package com.learn.Try.google.protobuf.test.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.learn.Try.google.protobuf.test.Student;
import com.learn.Try.google.protobuf.test.Teacher;
import com.google.gson.Gson;
/*
 * 测试生成 json文件
 */
public class GsonWriteTest {

	public static final int SIZE = 100;
	
	public static void main(String[] args) throws IOException {
		
		List<Student> stuList = new ArrayList<Student>();
		for (int i = 0; i < SIZE; i ++) {
			Student stu = new Student();
			stu.setAge(25);
			stu.setId(22);
			stu.setName("shun");
			
			stuList.add(stu);
		}
		
		
		Teacher teacher = new Teacher();
		teacher.setId(22);
		teacher.setName("shun");
		teacher.setStuList(stuList);
		
		String result = new Gson().toJson(teacher);
		FileWriter fw = new FileWriter("D:\\tmp\\test\\json" + SIZE);
		fw.write(result);
		fw.close();
	}

}
