package com.zzl.common.vo;

import java.io.Serializable;

/**
 * @author xhyzzzl
 */
public class FileDetail implements Serializable{
    private static final long serialVersionUID = 1149158775414483025L;
    private Integer id;
    private String realName;
    private String filePath;
    private String hostName;
    private String md5;
    private String suffix;
    private Long size;
    private String uploadDate;
    private String userId;
    private String userName;
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getRealName(){
        return realName;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getFilePath(){
        return filePath;
    }
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    public String getHostName(){
        return hostName;
    }
    public void setHostName(String hostName){
        this.hostName = hostName;
    }
    public String getMd5(){
        return md5;
    }
    public void setMd5(String md5){
        this.md5 = md5;
    }
    public String getSuffix(){
        return suffix;
    }
    public void setSuffix(String suffix){
        this.suffix = suffix;
    }
    public Long getSize(){
        return size;
    }
    public void setSize(Long size){
        this.size = size;
    }
    public String getUploadDate(){
        return uploadDate;
    }
    public void setUploadDate(String uploadDate){
        this.uploadDate = uploadDate;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    
}
