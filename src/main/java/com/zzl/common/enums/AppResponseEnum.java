package com.zzl.common.enums;

/**
 * @author: 张志龙
 * @Date: 2018/7/12 10:12
 * @Description:定义APP标准响应码
 */
public enum AppResponseEnum{
                            APP_RESPONSE_SUCCESS("200","请求响应成功!!!"),
                            APP_RESPONSE_ERROR("500","请求响应异常!!!"),
                            APP_LOGIN_SUCCESS("1001","用户登录验证通过!!!"),
                            APP_NO_LOGIN_NAME("1002","当前用户不存在!!!"),
                            APP_PASSWORD_ERROR("1003","当前用户密码错误!!!"),
                            APP_NO_DEVICECODE("1004","只允许绑定一台设备!!!"),
                            APP_SYSTEM_NO_PHONE("1005","系统中不存在此手机号!!!"),
                            APP_PARAM_EMPTY("1006","参数有空值!!!"),
                            APP_NO_USER("1007","当前用户不存在!!!"),
                            APP_VERIFICATION_CODE_ERROR("1008","验证码错误!!!"),
                            APP_PASSWORD_NO_MATCH("1009","密码与确认密码不匹配!!!"),
                            APP_FTP_NO_FILE("1010","FTP读取文件失败!!!"),
    APP_IP_NO_POWER("1011", "当前IP无权访问此接口!!!"),
    NO_COMPLETE_STUDY_TAPE("1012", "请完成所有课件学习后再提交"),
    PASSWORD_NO_ACCORD_RULE("1013", "密码必须至少包含大写字母、小写字母、数字或者特殊字符的其中三种并且长度不得小于8位"),
    LOGIN_NAME_OR_PASSWORD_NOT_RIGHT("1014", "账号不存在或者密码错误"),
    LOGIN_NOT_OPEN("1015", "该账号还未开放登录权限");
    
    private String code;
    
    private String message;
    
    AppResponseEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
    
    public String getCode(){
        return code;
    }
    
    public String getMessage(){
        return message;
    }
}
