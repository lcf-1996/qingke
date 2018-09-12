package com.qingke.service;

import java.util.List;

import com.qingke.common.beans.EUTreeNode;
import com.qingke.common.beans.QingKeResult;

public interface ContentCategoryService {

	List<EUTreeNode> getContentCategoryList(long parentid) throws Exception;

	QingKeResult addNode(long parentid, String name) throws Exception;

}
