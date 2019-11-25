package com.learn.redis.proto;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learn.hanjx.jpa.bean.Person;

public class UtilTest {
	private final static Logger logger = LoggerFactory.getLogger(UtilTest.class);

    public static void main(String[] args) {
    	Person p = new Person();
    	byte[] bs=ProtoUtil.serialize(p);
    	logger.info(Arrays.toString(bs));
	}
}
