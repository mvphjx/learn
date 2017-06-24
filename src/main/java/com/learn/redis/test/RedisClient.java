package com.learn.redis.test;

import redis.clients.jedis.Jedis;

public class RedisClient {
	
	/** 
     *  保存数据   类型为 Map 
     * <一句话功能简述> 
     * <功能详细描述> 
     * @param flag 
     * @param mapData 
     * @see [类、类#方法、类#成员] 
     */  
	public static void main(String[] args) {
		Jedis redisClient = null;  
		try  
		{  
			redisClient = RedisClientPoolTest.jedisPool.getResource();  
		}   
		catch (Exception e)  
		{  
			// 销毁对象  
			RedisClientPoolTest.jedisPool.returnBrokenResource(redisClient);  
		}  
		finally  
		{  
			// 还原到连接池  
			RedisClientPoolTest.jedisPool.returnResource(redisClient);  
		}  
		
	}

}
