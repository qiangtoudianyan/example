package com.zzl.common.interceptors;

import com.alibaba.fastjson.JSON;
import com.boco.mis.common.annotations.Anonymous;
import com.boco.mis.common.annotations.Operation;
import com.boco.mis.common.annotations.OperationType;
import com.boco.mis.common.basecontroller.BaseController;
import com.boco.mis.common.config.SessionConfig;
import com.boco.mis.common.constants.WebConstant;
import com.boco.mis.common.dal.entity.OperationLog;
import com.boco.mis.common.enums.WebResponseEnum;
import com.boco.mis.common.event.OperationLogEvent;
import com.boco.mis.common.support.ResponseData;
import com.boco.mis.common.utils.WebUtil;
import com.boco.mis.framework.common.utils.MapUtils;
import com.boco.mis.pc.dal.entity.OperationPower;
import com.boco.mis.pc.dal.entity.User;
import com.boco.mis.pc.services.UserCoreService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: 张志龙
 * @Date: 2018/7/3 22:37
 * @Description: 登录拦截器
 */
public class LoginInterceptor implements
                              HandlerInterceptor,
                              ApplicationEventPublisherAware{

    @Autowired
    private SessionConfig sessionConfig;

    private ApplicationEventPublisher applicationEventPublisher;
    
    @Autowired
    private UserCoreService userCoreService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
        if(!(handler instanceof HandlerMethod)){
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Object bean = handlerMethod.getBean();
        if(bean.getClass().isAnnotationPresent(Anonymous.class)
           || handlerMethod.getMethod().isAnnotationPresent(Anonymous.class)){
            return true;
        }
        if(!(bean instanceof BaseController)){
            throw new RuntimeException("not extends BaseController");
        }
        BaseController baseController = (BaseController)bean;
        Field field = BaseController.class.getDeclaredField("user");
        field.setAccessible(true);
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            responseByIsAjax(WebResponseEnum.USER_NO_LOGIN.getCode(),
                             WebResponseEnum.USER_NO_LOGIN.getMsg(),
                             request,
                             response);
            return false;
        }
        Operation operation = null;
        if(bean.getClass().isAnnotationPresent(Operation.class)){
            operation = bean.getClass().getAnnotation(Operation.class);
        }
        if(handlerMethod.getMethod().isAnnotationPresent(Operation.class)){
            operation =
                      handlerMethod.getMethod().getAnnotation(Operation.class);
        }
        if(null == operation){
            throw new RuntimeException("NO Operation Annotation");
        }
        String[] valueArr = operation.value();
        boolean isCommon=false;
        for (String value : valueArr) {
            if (WebConstant.DEFAULT_OPERATION.equals(value)){
                isCommon=true;
            }
        }
        boolean isOperationPower = false;
        if (!isCommon){
            List<OperationPower> operationPowers = user.getOperationPowers();
            if(operationPowers != null && operationPowers.size() != 0){
                for(OperationPower operationPower : user.getOperationPowers()){
                    String operationCode = operationPower.getOperationCode();
                    for (String value : valueArr) {
                        if(value.equals(operationCode)){
                            isOperationPower = true;
                            break;
                        }
                    }
                }
            }
        }
        if(isCommon || isOperationPower){
            if(!handlerMethod.getMethod()
                             .isAnnotationPresent(OperationType.class)){
                 throw new RuntimeException("NO OperationType Annotation");
            }else{
                OperationType operationType =
                                            handlerMethod.getMethod()
                                                         .getAnnotation(OperationType.class);
                OperationLog operationLog = new OperationLog();
                operationLog.setUserId(user.getUserId());
                operationLog.setLoginName(user.getLoginName());
                operationLog.setOperationTime(new Date());
                operationLog.setOperationIp(userCoreService.getIpAddr(request));
                Browser browser =
                                UserAgent.parseUserAgentString(request.getHeader("User-Agent"))
                                         .getBrowser();
                Version version =
                                browser.getVersion(request.getHeader("User-Agent"));
                operationLog.setOperationBrowser(browser.getName());
                operationLog.setOperationBrowserVersion(version.getVersion());
                operationLog.setOperationExplain(operationType.value()
                                                              .getOperationTypeExplain());
                operationLog.setOperationUri(request.getRequestURI());
                Map<String, Object> params =
                                           MapUtils.getMapFromRequestParameter(request);
                if(null != params){
                    operationLog.setOperationParams(JSON.toJSONString(params));
                }
                OperationLogEvent operationLogEvent =
                                                    new OperationLogEvent(operationLog);
                applicationEventPublisher.publishEvent(operationLogEvent);
            }
            field.set(baseController, user);
            stringRedisTemplate.expire("spring:session:id:"
                                       + user.getLoginName(),
                                       sessionConfig.getTimeout(),
                                       TimeUnit.SECONDS);
            return true;
        }
        responseByIsAjax(WebResponseEnum.UNAUTHORIZED_ACCESS.getCode(),
                         WebResponseEnum.UNAUTHORIZED_ACCESS.getMsg(),
                         request,
                         response);
        return false;
    }
    
    private void responseByIsAjax(String code,
                                  String msg,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException{
        if(WebUtil.isAjax(request)){
            response.setStatus(999);
            ResponseData responseData = new ResponseData();
            responseData.setCode(code);
            responseData.setMessage(msg);
            responseData.setData(request.getContextPath()
                                 + WebConstant.LOGIN_URL);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSON.toJSON(responseData).toString());
            printWriter.close();
        }else{
            response.sendRedirect(request.getContextPath()
                                  + WebConstant.LOGIN_URL);
        }
    }
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
