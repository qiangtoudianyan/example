package com.zzl.common.services;

import com.boco.mis.common.dal.entity.OperationLog;

/**
 * @author: 张志龙
 * @Date: 2018/8/20 18:11
 * @Description:
 */
public interface OperationLogService{
    /**
     * 插入操作日志
     * 
     * @param operationLog
     *            日志类
     */
    void insertOperationLog(OperationLog operationLog);
}
