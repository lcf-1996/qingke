package com.qingke.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingke.common.beans.EUTreeNode;
import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbItem;
import com.qingke.pojo.TbItemParam;
import com.qingke.service.ItemCatService;
import com.qingke.service.ItemParamService;
import com.qingke.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemCatService itemCatService;
	
	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/item-list.do")
	public String list() {
		return "item-list";
	}
	
	@RequestMapping("/item-add.do")
	public String add() {
		return "item-add";
	}
	
	@RequestMapping("/item-edit.do")
	public String edit() {
		return "item-edit";
	}
	
	@RequestMapping("/item-param.do")
	public String itemParam() {
		return "item-param-list";
	}
	@RequestMapping("/item-param-add.do")
	public String itemParamAdd() {
		return "item-param-add";
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
	
	@RequestMapping(value="/save.do", method=RequestMethod.POST)
	@ResponseBody
	private QingKeResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		QingKeResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}

	@RequestMapping(value="/desc.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public QingKeResult getItemDesc(@RequestParam(value="id")Long itemId) {
		QingKeResult result = itemService.getItemDesc(itemId);
		return result;
	}

	@RequestMapping(value="/update.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	private QingKeResult updateItem(TbItem item, String desc, String itemParams) throws Exception {
		QingKeResult result = itemService.updateItem(item, desc, itemParams);
		return result;
	}
	
	/**
	 * 根据商品类目查询商品规格参数模板
	 * @param itemCatId
	 * @return
	 */
	@RequestMapping(value="/item-param/query.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public QingKeResult getItemParamByCid(@RequestParam(value="catid") Long itemCatId) {
		QingKeResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}

	/**
	 * 添加商品规格参数模板
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping(value="/item-param/save.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public QingKeResult insertItemParam(@RequestParam(value="cid") Long cid, String paramData) {
		//创建商品规格模板对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		QingKeResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
	
	@RequestMapping(value="/item-param/list.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public EasyUIResult itemParamList(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="30")Integer rows) throws Exception {	
		EasyUIResult result = itemParamService.listItemParam(page, rows);
		return result;
	}
}

