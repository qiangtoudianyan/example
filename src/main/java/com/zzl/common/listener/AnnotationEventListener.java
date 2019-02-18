package com.zzl.common.listener;

import com.boco.mis.common.dal.entity.OperationLog;
import com.boco.mis.common.event.OperationLogEvent;
import com.boco.mis.common.services.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: 张志龙
 * @Date: 2018/8/19 21:52
 * @Description:
 */
@Component
public class AnnotationEventListener{
    @Autowired
    private OperationLogService operationLogService;
    
    @EventListener
    @Async
    public void onOperationLogEvent(OperationLogEvent operationLogEvent){
        OperationLog operationLog = (OperationLog)operationLogEvent.getSource();
        operationLogService.insertOperationLog(operationLog);
    }
}
