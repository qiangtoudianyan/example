package com.zzl.common.services.impl;

import com.boco.mis.common.constants.CommonConstant;
import com.boco.mis.common.services.LoginService;
import com.boco.mis.common.vo.CheckResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: 张志龙
 * @Date: 2018/11/5 16:20
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService{
    private final static String LOGIN_NAME_LOCKED = "当前账号已经锁定，请联系管理员";

    private final static String LOGIN_NAME_COUNT = "账号或密码错误(请先联系管理员确认账号是否存在)，当前剩余输入次数";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${app.login.count}")
    private Integer appLoginCount;

    @Value("${pc.login.count}")
    private Integer pcLoginCount;

    @Override
    public String checkLoginCount(String loginName, String loginType){
        long count =
                stringRedisTemplate.boundValueOps(loginType + "-" + loginName)
                                   .increment(1);
        if(CommonConstant.PC.equals(loginType)){
            return checkCount(count, pcLoginCount);
        }else if(CommonConstant.APP.equals(loginType)){
            return checkCount(count, appLoginCount);
        }
        return null;
    }

    @Override
    public CheckResult checkHasLocked(String loginName, String loginType){
        long count =
                stringRedisTemplate.boundValueOps(loginType + "-" + loginName)
                                   .increment(0);
        if(CommonConstant.PC.equals(loginType)){
            return checkHasLocked(count, pcLoginCount);
        }else if(CommonConstant.APP.equals(loginType)){
            return checkHasLocked(count, appLoginCount);
        }
        return null;
    }

    @Override
    public boolean unlockLogin(String loginName){
        try{
            stringRedisTemplate.delete(CommonConstant.APP
                                       + "-"
                                       + loginName);
            stringRedisTemplate.delete(CommonConstant.PC + "-" + loginName);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unlockAppLogin(String loginName){
        try{
            stringRedisTemplate.delete(CommonConstant.APP
                                       + "-"
                                       + loginName);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unlockPcLogin(String loginName){
        try{
            stringRedisTemplate.delete(CommonConstant.PC + "-" + loginName);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private String checkCount(long count, Integer loginCount){
        if(count >= loginCount){
            return LOGIN_NAME_LOCKED;
        }
        return LOGIN_NAME_COUNT + (loginCount - count);
    }

    private CheckResult checkHasLocked(long count, Integer loginCount){
        CheckResult checkResult = new CheckResult();
        if(count >= loginCount){
            checkResult.setResult(false);
            checkResult.setMsg(LOGIN_NAME_LOCKED);
        }else {
            checkResult.setResult(true);
        }
        return checkResult;
    }
}
