package com.zzl.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: 张志龙
 * @Date: 2018/8/10 23:52
 * @Description:
 */
@Component
public class LibreOfficeServerConfig{
    @Value("${libreOfficeServer.serverUrl}")
    private String libreOfficeServerUrl;
    
    public String getLibreOfficeServerUrl(){
        return libreOfficeServerUrl;
    }
    
    public void setLibreOfficeServerUrl(String libreOfficeServerUrl){
        this.libreOfficeServerUrl = libreOfficeServerUrl;
    }
}
