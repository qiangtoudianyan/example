package com.zzl.common.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: 张志龙
 * @Date: 2018/8/19 22:36
 * @Description:
 */
@Data
public class OperationLog{
    /**
     * ID
     */
    private Integer id;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 名字
     */
    private String loginName;
    
    /**
     * 操作时间
     */
    private Date operationTime;
    
    /**
     * 操作IP
     */
    private String operationIp;
    
    /**
     * 浏览器
     */
    private String operationBrowser;
    
    /**
     * 浏览器版本
     */
    private String operationBrowserVersion;
    
    /**
     * 操作URL
     */
    private String operationUri;
    
    /**
     * 入参
     */
    private String operationParams;
    
    /**
     * 说明
     */
    private String operationExplain;
    
}
