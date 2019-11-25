package com.learn.redis.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisJava {
	private static String keyDemo = "hjx";
	
	public static void main(String[] args) {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		System.out.println("Server is running: " + jedis.ping());
		jedis.append(keyDemo, "hanjianxiang");
	}

	@Test
	public void main() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		System.out.println("Server is running: " + jedis.ping());
		System.out.println("jedis.get: " + jedis.get(keyDemo));
		
	}
}