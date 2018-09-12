package com.qingke.rest;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis映射接口配置
 * 
 *
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {

		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		// 设置sqlSessionFactory名
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		// 设置接口映射器基础包名
		mapperScannerConfigurer.setBasePackage("com.qingke.mapper");
		return mapperScannerConfigurer;
	}

}
