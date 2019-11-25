package com.learn.Try.T2016.T10;

import javax.naming.CompositeName;
import javax.naming.InvalidNameException;
import javax.naming.Name;

public class T201609 {
	public static void main(String[] args) throws InvalidNameException {
		//javax.naming.CompositeName
		System.out.println("JNDI");
		Name name = new CompositeName("java:/YSAppSvr/ysabissvrlib/SvrJPASearch!cn.unibc.abis.net.tblq.opi.INetJPASearch");
	}

}
