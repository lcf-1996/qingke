package com.qingke.rest.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qingke.rest.util.RedisUtil;

/**
 * Reids客户端测试控制器
 *
 */
@RestController
public class RedisTestController {
	
	// 注入reids客户端工具类，在service或其他类使用类似方式注入使用即可
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @return
	 */
	@RequestMapping("/redis/set")
	public boolean index(@RequestParam("key")String key, @RequestParam("value")String value){
		return redisUtil.set(key, value);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@RequestMapping("/redis/get")
	public Object index(@RequestParam("key")String key){
		System.out.println((String)redisUtil.get(key));
		return redisUtil.get(key);
	}

}

