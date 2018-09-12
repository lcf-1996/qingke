package com.qingke.rest.service;

import java.util.List;

import com.qingke.pojo.TbContent;

/**
 * 内容业务接口
 * @author VIC
 *
 */
public interface ContentService {

	List<TbContent> getContentList(long contentCid);
}
