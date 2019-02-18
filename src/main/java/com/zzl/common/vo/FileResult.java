package com.zzl.common.vo;

import lombok.Data;

@Data
public class FileResult {
	//上传是否成功
	private Boolean success=Boolean.FALSE;
	private String info;
	private String fileName;
	private Long fileId;
	private String userName;
	private String userId;
	private Boolean isCanView=Boolean.FALSE;
	private String uid;
	private String url;
	public FileResult fileName(String fileName) {
		this.fileName=fileName;
		return this;
	}
	public FileResult url(String url) {
		this.url=url;
		return this;
	}
	public FileResult info(String info) {
		this.info=info;
		return this;
	}
	public FileResult user(String userId,String userName) {
		this.userId=userId;
		this.userName=userName;
		return this;
	}
	public FileResult uid(String uid) {
		this.uid=uid;
		return this;
	}
	public FileResult fileId(Long fileId) {
		this.fileId=fileId;
		return this;
	}
}
