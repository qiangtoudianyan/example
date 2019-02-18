package com.zzl.common.enums;

/**
 * @author: 张志龙
 * @Date: 2018/7/8 16:48
 * @Description:
 */
public enum WebResponseEnum {
    SUCCESS("200", "响应成功!!!"),
    ERROR("500", "系统异常!!!"),
    USER_NO_LOGIN("-1", "当前用户没有登录!!!"),
    UNAUTHORIZED_ACCESS("-2", "无权访问!!!"),
    LOGINNAME_IS_EMPTY("2001", "账号为空"),
    PASSWORD_IS_EMPTY("2002", "密码为空"),
    LOGINNAME_NOT_RIGHT("2003", "账号不存在"),
    PASSWORD_NOT_RIGHT("2004", "密码错误"),
    USER_NO_DISPOSITION_DATA_POWER("2005",
            "当前用户没有配置数据权限!!!"),
    USER_NO_DISPOSITION_OPERATION_POWER("2006",
            "当前用户没有配置操作权限!!!"),
    USER_INFO_IMPORT_FAIL("2007",
            "导入信息不合法,具体错误请点击确定下载文件查看"),
    USER_INFO_IMPORT_TEMPLATE_IS_EMPTY("2008",
            "不能导入空模板,请检查"),
    PARAMS_IS_EMPTY("2009", "参数有空值"),
    STUDY_PLAN_ING("2010", "培训计划生成中"),
    LOGINNAME_OR_PASSWORD_NOT_RIGHT("2011", "账号不存在或密码错误"),
    NOT_FIND_SHEET_NAME("2012", "该Excel中未找到名称为“用户信息”的Sheet页，请检查！"),
    LOGIN_NOT_OPEN("2013", "该账号还未开放登录权限"),
    LOGIN_STATUS_YES("2014", "登录状态检查通过"),
    LOGIN_STATUS_NO("2015", "该账号已在其他客户端登录，确认将其踢出并登录?"),
    USER_HAS_STUDY_PLAN("2016", "该账号培训计划已存在，不允许重新生成！"),
    NO_STUDY_PLAN_YEAR("2017", "年份不允许超过当前的年份！"),
    NO_THIS_LOGIN_NAME("2018", "当前账号不存在，请检查！");

    private String code;

    private String msg;

    WebResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
