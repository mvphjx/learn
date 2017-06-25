package com.learn.redis.test.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.learn.redis.model.RedisDemo;
import com.learn.redis.proto.ProtoUtil;
import com.learn.redis.test.RedisClientPool;

/**
 * 测试一下 利用自定义序列化  保存和获取对象
 * @author han
 *
 */
public class ObjectSaveAndGet {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Test
	public void set() {
		String key = "RedisDemo:hjx";
		RedisDemo model = new RedisDemo();
		model.setName("韩健祥");
		model.setBirthDay(new Date());
		List<String> list = new ArrayList<String>();
		list.add("list1");
		model.setList(list);
		try (Jedis jedis = RedisClientPool.jedisPool.getResource()) {
            byte[] bytes = ProtoUtil.serialize(model);
            // 超时缓存
            int timeout=60*60;
            String result =jedis.setex(key.getBytes(), timeout, bytes);
            logger.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Test
	public void get() {
		String key = "RedisDemo:hjx";
		Jedis redisClient = RedisClientPool.jedisPool.getResource();
		byte[] bytes = redisClient.get(key.getBytes());
		if (bytes != null) {
             // 空对象
			RedisDemo model =ProtoUtil.deserialize(bytes, RedisDemo.class);
            logger.info(model.toString());
        }
	}

}
