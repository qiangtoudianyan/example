package com.zzl.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: 张志龙
 * @Date: 2018/8/19 21:25
 * @Description:
 */
public class OperationLogEvent extends ApplicationEvent {

    public OperationLogEvent(Object source) {
        super(source);
    }
}
