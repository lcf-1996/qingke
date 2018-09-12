package com.qingke.service;

import com.qingke.common.beans.EasyUIResult;
import com.qingke.common.beans.QingKeResult;
import com.qingke.pojo.TbContent;

public interface ContentService {

	EasyUIResult getContentList(long catId, Integer page, Integer rows) throws Exception;

	QingKeResult addContent(TbContent content) throws Exception;

}
