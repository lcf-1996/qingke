package com.qingke.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.util.HttpClientUtil;
import com.common.util.JsonUtils;
import com.qingke.common.beans.ADItem;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbContent;
import com.qingke.portal.service.AdService;


@Service
public class ADServiceImpl implements AdService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String INDEX_AD1_URL;
	
	@Override
	public String getAdItemList() throws Exception {
		//调用服务层的服务查询打广告位的数据
		String result = HttpClientUtil.doGet(REST_BASE_URL + INDEX_AD1_URL);
		//把json数据转换成对象
		QingKeResult qingKeResult = QingKeResult.formatToList(result, TbContent.class);
		List<ADItem>itemList = new ArrayList<>();
		if (qingKeResult.getStatus() == 200 ) {
			List<TbContent>contentList = (List<TbContent>) qingKeResult.getData();
			for (TbContent tbContent : contentList) {
				ADItem item = new ADItem();
				item.setHeight(240);
				item.setWidth(670);
				item.setSrc(tbContent.getPic());
				item.setHeightB(240);
				item.setWidth(550);
				item.setSrcB(tbContent.getPic2());
				item.setAlt(tbContent.getTitleDesc());
				item.setHref(tbContent.getUrl());
				itemList.add(item);
			}
			
		}
		return JsonUtils.objectToJson(itemList);
	}

}
