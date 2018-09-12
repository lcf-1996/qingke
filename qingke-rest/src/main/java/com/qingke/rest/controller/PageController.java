package com.qingke.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 页面跳转控制器
 * @author VIC
 *
 */
@Controller
public class PageController {
	

	
	/**
	 * 后台首页
	 * @return
	 * @throws Exception  
	 * 
	 */
	@RequestMapping("/")
	public String index() throws Exception {
		
		return "index";
	}
	
	/**
	 * 其他页面
	 * @param page 页面名称
	 * @return
	 */
	@RequestMapping("/{page}")
	public String page(@PathVariable String page){
		return page;
	}

}

