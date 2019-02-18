package com.zzl.common.constants;

/**
 * @author: 杨志强
 * @Date: 2018/7/8 16:30
 * @Description:
 */
public class WebUserManagementConstant{
    /**
     * 用户管理表头code(开启)
     */
    public final static String MANAGEMETN_HEAD_CODE_OPEN = "userManagementOpen";
    
    /**
     * 用户管理表头code(停用)
     */
    public final static String MANAGEMETN_HEAD_CODE_STOP = "userManagementStop";
    
    /**
     * 用户启用状态
     */
    public final static Integer USER_OPEN_STATE = 1;
    
    /**
     * 用户停用状态
     */
    public final static Integer USER_STOP_STATE = 0;
    
    /**
     * 用户按钮状态(停用)
     */
    public final static String USER_BUTTON_STATE_STOP = "stop";
    
    /**
     * 用户按钮状态(启用)
     */
    public final static String USER_BUTTON_STATE_OPEN = "open";
    
    /**
     * 用户按钮状态(设备重置)
     */
    public final static String USER_BUTTON_STATE_EQP = "eqp";
    
    /**
     * 用户按钮状态(重置密码)
     */
    public final static String USER_BUTTON_STATE_PASSWORD = "password";
    
    /**
     * 获取用户唯一值code
     */
    public final static String USER_SOLE_CODE = "user";
    
    /**
     * 新增用户
     */
    public final static String USER_STATE_SAVE = "saveUser";
    
    /**
     * 修改用户
     */
    public final static String USER_STATE_UPDATE = "updateUser";
    
    /**
     * 设置角色
     */
    public final static String ROLE_SET = "roleSet";
    
    /**
     * 查看用户
     */
    public final static String USER_STATE_LOOK = "lookUser";
    
    /**
     * 用户默认密码类型
     */
    public final static String USER_PASSWORD_TYPE = "initPassword";
    
    /**
     * 用户数据生效状态
     */
    public final static Integer USER_VALID_STATE = 1;
    
    /**
     * 用户数据失效状态(备份)
     */
    public final static Integer USER_FAILURE_STATE = 0;
    
    /**
     * 用户初始化版本号
     */
    public final static Integer USER_INIT_VERSION = 1;
    
    /**
     * 管理员
     */
    public final static Integer ADMINISTRATOR = 1;
    
    /**
     * 系统管理员
     */
    public final static Integer SYSTEM_ADMINISTRATOR = 2;
    
    /**
     * 超级管理员
     */
    public final static Integer SUPER_ADMINISTRATOR = 3;
    
    /**
     * 新增组织机构
     */
    public final static String SAVE_ORGANIZATION = "save";
    
    /**
     * 修改组织机构
     */
    public final static String UPDATE_ORGANIZATION = "update";
    
    /**
     * 组织机构唯一ID编码
     */
    public final static String ORGANIZATION_SOLE_CODE = "org";
    
    /**
     * 角色类型(管理)
     */
    public final static String ROLE_TYPE_ADMINISTRATOR = "1";
    
    /**
     * 角色类型(业务)
     */
    public final static String ROLE_TYPE_BUSINESS = "2";
    
    /**
     * 统计报表
     */
    public final static String COUNT_REPORT = "countReport";
}
