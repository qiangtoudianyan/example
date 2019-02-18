package com.zzl.common.services;

import com.boco.mis.common.vo.CheckResult;

/**
 * @author: 张志龙
 * @Date: 2018/11/5 15:37
 * @Description:
 */
public interface LoginService{

    String checkLoginCount(String loginName, String loginType);

    CheckResult checkHasLocked(String loginName, String loginType);

    boolean unlockLogin(String loginName);

    boolean unlockAppLogin(String loginName);

    boolean unlockPcLogin(String loginName);
}
