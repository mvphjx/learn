package com.learn.Try.google.protobuf.test.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.learn.Try.google.protobuf.test.StudentProto.Student;
import com.learn.Try.google.protobuf.test.TeacherProto.Teacher;

public class ProtoReadTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Teacher teacher = Teacher.parseFrom(new FileInputStream("D:\\tmp\\test\\aaa.protoout"));
		System.out.println("Teacher ID:" + teacher.getId() + ",Name:" + teacher.getName());
		for (Student stu:teacher.getStudentListList()) {
			System.out.println("Student ID:" + stu.getId() + ",Name:" + stu.getName() + ",Age:" + stu.getAge());
		}
	}

}
