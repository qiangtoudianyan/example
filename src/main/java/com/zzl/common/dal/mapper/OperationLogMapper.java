package com.zzl.common.dal.mapper;

import com.boco.mis.common.dal.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: 张志龙
 * @Date: 2018/8/21 09:05
 * @Description:
 */
@Mapper
public interface OperationLogMapper{
    /**
     * 插入操作日志
     *
     * @param operationLog
     *            日志类
     */
    void insertOperationLog(@Param("operationLog") OperationLog operationLog);
}
