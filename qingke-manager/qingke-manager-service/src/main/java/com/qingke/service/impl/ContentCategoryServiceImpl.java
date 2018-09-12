package com.qingke.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingke.common.beans.EUTreeNode;
import com.qingke.common.beans.QingKeResult;
import com.qingke.mapper.TbContentCategoryMapper;
import com.qingke.pojo.TbContentCategory;
import com.qingke.pojo.TbContentCategoryExample;
import com.qingke.pojo.TbContentCategoryExample.Criteria;
import com.qingke.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getContentCategoryList(long parentid) throws Exception {
		
		//根据parentid查询内容分类列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentid);
		List<TbContentCategory>list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode>resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			//判断是否是父节点
			if (tbContentCategory.getIsParent()) {
				node.setState("closed");
			} else {
				node.setState("open");
			}
			resultList.add(node);
		}
		return resultList;
	}
	
	@Override
	public QingKeResult addNode(long parentid, String name) throws Exception {
		Date date = new Date();
		//添加一个新节点
		//创建一个节点对象
		TbContentCategory node = new TbContentCategory();
		node.setName(name);
		node.setParentId(parentid);
		node.setIsParent(false);
		node.setCreated(date);
		node.setUpdated(date);
		node.setSortOrder(1);
		//状态。可选值:1(正常),2(删除)
		node.setStatus(1);
		//插入新节点。需要返回主键
		contentCategoryMapper.insert(node);
		//判断如果父节点的isparent不是true修改为true
		//取父节点的内容
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentid);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		//把新节点返回
		return QingKeResult.ok(node);
	}

	

}
