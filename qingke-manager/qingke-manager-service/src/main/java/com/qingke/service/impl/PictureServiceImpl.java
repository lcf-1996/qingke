package com.qingke.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.util.FtpUtil;
import com.common.util.IDUtils;
import com.qingke.service.PictureService;

/**
 * 图片服务接口实现类
 * @author VIC
 *
 */
@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("#{configProperties['FTP_ADDRESS']}")
	private String FTP_ADDRESS;
	@Value("#{configProperties['FTP_PORT']}")
	private Integer FTP_PORT;
	@Value("#{configProperties['FTP_USERNAME']}")
	private String FTP_USERNAME;
	@Value("#{configProperties['FTP_PASSWORD']}")
	private String FTP_PASSWORD;
	@Value("#{configProperties['FTP_BASE_PATH']}")
	private String FTP_BASE_PATH;
	@Value("#{configProperties['IMAGE_BASE_URL']}")
	private String IMAGE_BASE_URL;
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try {
			//生成一个新的文件名
			//取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			//生成新文件名
			//UUID.randomUUID();
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			//图片上传
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					FTP_BASE_PATH,  imagePath, newName, uploadFile.getInputStream());
			//返回结果
			if(!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
			return resultMap;
			
		} catch (Exception e) {
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			return resultMap;
		}
	}

}

