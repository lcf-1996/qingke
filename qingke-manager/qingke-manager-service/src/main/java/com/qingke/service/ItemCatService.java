package com.qingke.service;

import java.util.List;

import com.qingke.common.beans.EUTreeNode;

public interface ItemCatService {
	List<EUTreeNode> getCatList(long parentId);
}
