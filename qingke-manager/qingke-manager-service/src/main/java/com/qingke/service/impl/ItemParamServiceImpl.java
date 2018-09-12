package com.qingke.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.mapper.TbItemParamMapper;
import com.qingke.pojo.TbItem;
import com.qingke.pojo.TbItemExample;
import com.qingke.pojo.TbItemParam;
import com.qingke.pojo.TbItemParamExample;
import com.qingke.pojo.TbItemParamExample.Criteria;
import com.qingke.service.ItemParamService;

/**
 * 商品规格参数模板管理接口实现类
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public QingKeResult getItemParamByCid(Long cid) {
		
		// 创建查询条件
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		if(cid != null) {	
			criteria.andItemCatIdEqualTo(cid);
		}
		// 执行查询
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return QingKeResult.ok(list.get(0));
		}
		
		return QingKeResult.ok();
	}
	
	 // 实现方法
	@Override
	public QingKeResult insertItemParam(TbItemParam itemParam) {
		// 设置创建和更新日期
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return QingKeResult.ok();
	}

	@Override
	public EasyUIResult listItemParam(Integer page, Integer rows) throws Exception {
		TbItemParamExample example = new TbItemParamExample();
		// 设置分页
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 取分页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

}

