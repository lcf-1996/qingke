package com.qingke.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.util.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.mapper.TbItemDescMapper;
import com.qingke.mapper.TbItemMapper;
import com.qingke.mapper.TbItemParamItemMapper;
import com.qingke.pojo.TbItem;
import com.qingke.pojo.TbItemDesc;
import com.qingke.pojo.TbItemExample;
import com.qingke.pojo.TbItemParamItem;
import com.qingke.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public EasyUIResult getItemList(Integer page, Integer rows) throws Exception {
		TbItemExample example = new TbItemExample();
		// 设置分页
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);

		return result;

	}

	@Override
	public QingKeResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		// item补全
		// 生成商品ID
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除',
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 插入到数据库
		itemMapper.insert(item);
		// 添加商品描述信息
		QingKeResult result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		// 添加规格参数
		result = insertItemParamItem(itemId, itemParam);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		
		return QingKeResult.ok();
	}

	/**
	 * 添加商品描述
	 * <p>
	 * Title: insertItemDesc
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param desc
	 */
	private QingKeResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return QingKeResult.ok();
	}

	/**
	 * 添加规格参数
	 * <p>
	 * Title: insertItemParamItem
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	private QingKeResult insertItemParamItem(Long itemId, String itemParam) {
		// 创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		// 向表中插入数据
		itemParamItemMapper.insert(itemParamItem);

		return QingKeResult.ok();

	}

	@Override
	public QingKeResult getItemDesc(long itemId) {
		// 查询
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return QingKeResult.ok(itemDesc);
	}

	@Override
	public QingKeResult updateItem(TbItem item, String desc, String itemParam) throws Exception {

		// 设置修改时间
		item.setUpdated(new Date());
		// 根据主键修改商品
		itemMapper.updateByPrimaryKeySelective(item);
		// 修改商品描述信息
		QingKeResult result = updateItemDesc(item.getId(), desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		//添加规格参数
		result = insertItemParamItem(item.getId(), itemParam);
		if (result.getStatus() != 200) {
					throw new Exception();
		}
		return QingKeResult.ok();
	}

	/**
	 * 修改商品描述
	 * 
	 * @param itemId
	 * @param desc
	 * @return
	 */
	private QingKeResult updateItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		return QingKeResult.ok();
	}

}
