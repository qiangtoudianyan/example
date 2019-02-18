package com.zzl.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: 张志龙
 * @Date: 2018/7/21 17:36
 * @Description: 附件系统配置类
 */
@Component
@Data
public class FileConfig {
	/**
	 * 附件服务地址
	 */
	@Value("${file.microUrl}")
	private String microUrl;

	/**
	 * 系统编码
	 */
	@Value("${file.systemCode}")
	private String systemCode;

}
