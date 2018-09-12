package com.qingke.service;

import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbItemParam;

/**
 * 商品规格参数模板业务接口
 *
 */
public interface ItemParamService {
	
	/**
	 * 根据商品类目查询商品规格参数模板
	 * @param cid
	 * @return
	 */
	QingKeResult getItemParamByCid(Long cid);
	
	/**
	 * 添加商品规格参数模板
	 * @param itemParam
	 * @return
	 */
	QingKeResult insertItemParam(TbItemParam itemParam);

	EasyUIResult listItemParam(Integer page, Integer rows) throws Exception;

}
