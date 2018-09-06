package com.qingke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingke.common.beans.EUTreeNode;
import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbItem;
import com.qingke.service.ItemCatService;
import com.qingke.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/item-list.do")
	public String list() {
		return "item-list";
	}
	
	@RequestMapping("/item-add.do")
	public String add() {
		return "item-add";
	}
	
	//设置相应的内容为json数据
	@RequestMapping(value="/list.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public EasyUIResult getItemlist(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="30")Integer rows) throws Exception {
		//查询商品列表
		EasyUIResult result = itemService.getItemList(page, rows);
		
		return result;
	}
	
	//设置相应的内容为json数据
	@RequestMapping(value="/cat/list.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public List<EUTreeNode> getItemAll(@RequestParam(value="id",defaultValue="0")Long parentId) throws Exception {
		List<EUTreeNode> catList = itemCatService.getCatList(parentId);
		return catList;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	private QingKeResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		QingKeResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}

}

