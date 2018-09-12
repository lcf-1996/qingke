package com.qingke.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingke.portal.service.AdService;


/**
 * 页面跳转控制器
 * 
 */
@Controller
public class PageController {
	@Autowired
	private AdService adService;

	/**
	 * 首页
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/")
	public String index(Model model) throws Exception {
		String adResult = adService.getAdItemList();
		model.addAttribute("ad1", adResult);
		return "index";
	}

	/**
	 * 其他页面
	 * 
	 * @param page 页面名称
	 * @return
	 */
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}

}
