package com.qingke.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.util.JsonUtils;
import com.qingke.mapper.TbContentMapper;
import com.qingke.pojo.TbContent;
import com.qingke.pojo.TbContentExample;
import com.qingke.pojo.TbContentExample.Criteria;
import com.qingke.rest.service.ContentService;

/**
 * 内容业务接口实现
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public List<TbContent> getContentList(long contentCid) {
		
		//根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		try {
			//把list转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
