package com.qingke.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qingke.service.PictureService;

/**
 * 上传图片控制器
 *
 */
@Controller
@RequestMapping("/pic")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(value="/upload.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map pictureUpload(MultipartFile uploadFile) {
		Map result = pictureService.uploadPicture(uploadFile);
		//为了保证功能的兼容性，需要把Result转换成json格式的字符串。
		//String json = JsonUtils.objectToJson(result);
		return result;
	}
}

