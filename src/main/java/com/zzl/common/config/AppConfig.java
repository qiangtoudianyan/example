package com.zzl.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: 张志龙
 * @Date: 2018/8/20 11:26
 * @Description:
 */
@Component
@Data
public class AppConfig{
    
    @Value("${app.access.ips}")
    private String appAccessIps;
}
