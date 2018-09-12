package com.qingke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbContent;
import com.qingke.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content.do")
	public String contentCat() {
		
		return "content";
	}
	
	@RequestMapping("/content-add.do")
	public String contentAdd() {
		
		return "content-add";
	}
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResult getContentList(Long categoryId, Integer page, Integer rows) throws Exception {
		EasyUIResult result = contentService.getContentList(categoryId, page, rows);
		
		return result;
		
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public QingKeResult addContent(TbContent content) throws Exception {
		QingKeResult result = contentService.addContent(content);
		return result;
	}

}

