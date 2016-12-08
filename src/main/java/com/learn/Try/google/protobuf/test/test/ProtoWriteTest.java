package com.learn.Try.google.protobuf.test.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.learn.Try.google.protobuf.test.StudentProto.Student;
import com.learn.Try.google.protobuf.test.TeacherProto.Teacher;

public class ProtoWriteTest {

	public static final int SIZE = 100;
	
	public static void main(String[] args) throws IOException {
		
		//构造List
		List<Student> stuBuilderList = new ArrayList<Student>();
		for (int i = 0; i < SIZE; i ++) {
			Student.Builder stuBuilder = Student.newBuilder();
			stuBuilder.setAge(25);
			stuBuilder.setId(11);
			stuBuilder.setName("shun");
			
			stuBuilderList.add(stuBuilder.build());
		}
		
		Teacher.Builder teaBuilder = Teacher.newBuilder();
		teaBuilder.setId(1);
		teaBuilder.setName("testTea");
		teaBuilder.addAllStudentList(stuBuilderList);
		
		//把gpb写入到文件
		FileOutputStream fos = new FileOutputStream("D:\\tmp\\test\\proto-" + SIZE);
		teaBuilder.build().writeTo(fos);
		fos.close();
	}

}
