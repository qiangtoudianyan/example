package com.zzl.common.services.impl;

import com.boco.mis.common.dal.entity.OperationLog;
import com.boco.mis.common.dal.mapper.OperationLogMapper;
import com.boco.mis.common.services.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 张志龙
 * @Date: 2018/8/20 18:12
 * @Description:
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public void insertOperationLog(OperationLog operationLog) {
        operationLogMapper.insertOperationLog(operationLog);
    }
}
