package com.qingke.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingke.common.beans.ItemCat;
import com.qingke.common.beans.ItemCatResult;
import com.qingke.mapper.TbItemCatMapper;
import com.qingke.pojo.TbItemCat;
import com.qingke.pojo.TbItemCatExample;
import com.qingke.pojo.TbItemCatExample.Criteria;
import com.qingke.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public ItemCatResult queryAllCategory() throws Exception {
		
		ItemCatResult result = new ItemCatResult();
		result.setData(getItemCatList(0l));
		
		return result;
	}
	
	/**
	 * 查询分类列表
	 * <p>Title: getItemCatList</p>
	 * <p>Description: </p>
	 * @param parentid
	 * @return
	 */
	private List<?> getItemCatList(long parentid) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//查询parentid为0的分类信息
		criteria.andParentIdEqualTo(parentid);
		List<TbItemCat>list = itemCatMapper.selectByExample(example);
		List dataList = new ArrayList();
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				ItemCat itemCat = new ItemCat();
				itemCat.setUrl("/category/" + tbItemCat.getId() + ".html");
				itemCat.setName(tbItemCat.getName());
				//递归调用
				itemCat.setItem(getItemCatList(tbItemCat.getId()));
				//添加到列表
				dataList.add(itemCat);
			} else {
				String catItem = "/item/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
				dataList.add(catItem);
			}
		}
		return dataList;
	}

}
