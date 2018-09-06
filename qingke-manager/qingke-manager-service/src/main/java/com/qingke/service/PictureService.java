package com.qingke.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片服务接口
 * @author VIC
 *
 */
public interface PictureService {

	/**
	 * 上传图片
	 * @param uploadFile
	 * @return
	 */
	Map uploadPicture(MultipartFile uploadFile);
}

