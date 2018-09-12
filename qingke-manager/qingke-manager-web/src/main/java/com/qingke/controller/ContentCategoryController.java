package com.qingke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingke.common.beans.EUTreeNode;
import com.qingke.common.beans.QingKeResult;
import com.qingke.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content-category.do")
	public String contentCat() {
		
		return "content-category";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode>getContentCategoryList(@RequestParam(value="id", defaultValue="0")long parentid) throws Exception {
		List<EUTreeNode> list = contentCategoryService.getContentCategoryList(parentid);
		
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public QingKeResult addNode(Long parentId, String name) throws Exception {
		
		QingKeResult result = contentCategoryService.addNode(parentId, name);
		
		return result;
	}

}

