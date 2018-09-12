package com.qingke.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.ExceptionUtil;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbContent;
import com.qingke.rest.service.ContentService;


/**
 * 内容控制器
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public QingKeResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<TbContent> list = contentService.getContentList(contentCategoryId);
			return QingKeResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return QingKeResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}