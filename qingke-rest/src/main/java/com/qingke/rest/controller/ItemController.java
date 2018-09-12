package com.qingke.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.JsonUtils;
import com.qingke.common.beans.ItemCatResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.rest.service.ItemCatService;
import com.qingke.rest.service.ItemService;

/**
 * 商品控制器
 * 
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public QingKeResult getItemDesc(@PathVariable Long itemId) {
		QingKeResult result = itemService.getItemDesc(itemId);
		return result;
	}

	@RequestMapping(value="/all", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String queryAll(String callback) throws Exception {
		//查询分类列表
		ItemCatResult result = itemCatService.queryAllCategory();
		//把对象转换成json数据
		String jsonResult = JsonUtils.objectToJson(result);
		//拼接字符串
		String resultStr = callback + "(" + jsonResult + ");";
		
		return resultStr;
	}


}
