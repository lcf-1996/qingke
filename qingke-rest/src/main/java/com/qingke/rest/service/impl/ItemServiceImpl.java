package com.qingke.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingke.common.beans.QingKeResult;
import com.qingke.mapper.TbItemDescMapper;
import com.qingke.pojo.TbItemDesc;
import com.qingke.rest.service.ItemService;

/**
 * 商品业务接口实现类
 * 
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public QingKeResult getItemDesc(long itemId) {
		//创建查询条件
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

		return QingKeResult.ok(itemDesc);
	}

}
