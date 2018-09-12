package com.qingke.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.mapper.TbContentMapper;
import com.qingke.pojo.TbContent;
import com.qingke.pojo.TbContentExample;
import com.qingke.pojo.TbContentExample.Criteria;
import com.qingke.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public EasyUIResult getContentList(long catId, Integer page, Integer rows) throws Exception {
		//根据category_id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbContent>list = contentMapper.selectByExampleWithBLOBs(example);
		//取分页信息
		PageInfo<TbContent>pageInfo = new PageInfo<>(list);
		EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), list);
		return result;
	}
	
	@Override
	public QingKeResult addContent(TbContent content) throws Exception {
		
		//把图片信息保存至数据库
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//把内容信息添加到数据库
		contentMapper.insert(content);
		
		return QingKeResult.ok();
	}


}

