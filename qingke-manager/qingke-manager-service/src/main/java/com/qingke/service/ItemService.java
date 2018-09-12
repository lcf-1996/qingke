package com.qingke.service;

import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbItem;

public interface ItemService {
	EasyUIResult getItemList(Integer page, Integer rows) throws Exception;
	
	QingKeResult createItem(TbItem item, String desc, String itemParam) throws Exception;
	
	/**
	* 获取商品详情
	* @param itemId
	* @return
	*/
	QingKeResult getItemDesc(long itemId);

	/**
	* 修改商品
	* @param item
	* @param desc
	* @param itemParam
	* @return
	* @throws Exception
	*/
	QingKeResult updateItem(TbItem item, String desc, String itemParam) throws Exception;

}
