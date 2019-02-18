package com.zzl.common.interceptors;

import com.alibaba.fastjson.JSON;
import com.boco.mis.common.config.AppConfig;
import com.boco.mis.common.enums.AppResponseEnum;
import com.boco.mis.common.support.ResponseData;
import com.boco.mis.pc.services.UserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: 张志龙
 * @Date: 2018/7/3 22:37
 * @Description: 登录拦截器
 */
public class AppInterceptor implements HandlerInterceptor{
    
    private final static String LOCALHOST_VALUE = "0:0:0:0:0:0:0:1,127.0.0.1";
    
    private final static String LOCALHOST = "localhost";
    
    @Autowired
    private AppConfig appConfig;
    
    @Autowired
    private UserCoreService userCoreService;
    
    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException{
        String appAccessIps = appConfig.getAppAccessIps();
        String strClientIp = userCoreService.getIpAddr(request);
        if(StringUtils.isEmpty(appAccessIps)){
            return true;
        }
        String[] appAccessIpArr = appAccessIps.split(",");
        for(String appAccessIp : appAccessIpArr){
            boolean flag1 = LOCALHOST.equals(appAccessIp)
                            && LOCALHOST_VALUE.contains(strClientIp);
            boolean flag2 = appAccessIp.equalsIgnoreCase(strClientIp);
            if(flag1 || flag2){
                return true;
            }
        }
        ResponseData responseData = new ResponseData();
        responseData.setCode(AppResponseEnum.APP_IP_NO_POWER.getCode());
        responseData.setMessage(AppResponseEnum.APP_IP_NO_POWER.getMessage());
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(responseData));
        printWriter.flush();
        printWriter.close();
        return false;
    }
    
}
