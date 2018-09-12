package com.qingke.rest.service;

import com.qingke.common.beans.QingKeResult;

/**
* 商品业务接口
*
*/
public interface ItemService {
	QingKeResult getItemDesc(long itemId);
}

