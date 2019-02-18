package com.zzl.common.vo;

import lombok.Data;

@Data
public class FileVo {
	/**
	 * 唯一Id
	 */
	private Integer fileId;

	/**
	 * 文件真实名称
	 */
	private String realName;

	/**
	 * 上传时间
	 */
	private String uploadDate;

	/**
	 * 大小
	 */
	private Long size;

	/**
	 * md5
	 */
	private String md5;

	/**
	 * 系统编码
	 */
	private String systemCode;

	/**
	 * 模块编码
	 */
	private String modelCode;

	/**
	 * 附件类型编码
	 */
	private String attachType;

	/**
	 * 附件名称
	 */
	private String attachName;

	/**
	 * 删除时间
	 */
	private String deleteDate;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 下载URL
	 */
	private String url;
}
