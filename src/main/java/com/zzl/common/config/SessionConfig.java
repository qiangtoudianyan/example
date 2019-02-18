package com.zzl.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: 张志龙
 * @Date: 2018/11/10 11:49
 * @Description:
 */
@Component
@Getter
@Setter
public class SessionConfig{
    @Value("${session.maxInactiveIntervalInSeconds}")
    private Long timeout;
}
