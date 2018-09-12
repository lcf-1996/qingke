package com.qingke.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisClientTest {

	// 单实例连接redis
	@Test
	public void testJedisSingle() {

		Jedis jedis = new Jedis("192.168.1.9", 6379);
		jedis.auth("root");
		jedis.set("name", "bar");
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();

	}

}
