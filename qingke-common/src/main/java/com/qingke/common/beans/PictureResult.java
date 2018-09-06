package com.qingke.common.beans;

public class PictureResult {
	/**
	 * 上传图片返回值，成功：0	失败：1	
	 */
	private Integer error;
	/**
	 * 回显图片使用的url
	 */
	private String url;
	/**
	 * 错误时的错误消息
	 */
	private String message;
}
