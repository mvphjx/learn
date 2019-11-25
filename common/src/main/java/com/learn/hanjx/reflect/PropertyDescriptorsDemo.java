package com.learn.hanjx.reflect;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import com.learn.hanjx.reflect.dao.StudentInfo;

public class PropertyDescriptorsDemo {
	public static void main(String[] args) throws IntrospectionException {
		PropertyDescriptor[] descriptors=Introspector.getBeanInfo(StudentInfo.class, Object.class).getPropertyDescriptors();
		for(PropertyDescriptor p :descriptors){
			//似乎 无法操作 注解
			System.out.println(p.toString());
			
		}
		
	}

}
