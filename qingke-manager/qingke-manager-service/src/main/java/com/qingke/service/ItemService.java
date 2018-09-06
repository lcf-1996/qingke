package com.qingke.service;

import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbItem;

public interface ItemService {
	EasyUIResult getItemList(Integer page, Integer rows) throws Exception;
	
	QingKeResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}
