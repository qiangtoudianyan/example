package com.zzl.common.constants;

import java.util.UUID;

/**
 * @author: 张志龙
 * @Date: 2018/7/8 16:30
 * @Description:
 */
public class StudyPlanConstant{
    
    /**
     * 获取锁的KEY
     */
    public final static String STUDY_PLAN_LOCK_KEY = "STUDY_PLAN_LOCK_KEY";
    
    /**
     * 生成执行计划的客户端ID
     */
    public final static String STUDY_PLAN_CLIENT_ID = UUID.randomUUID()
                                                          .toString();
    
    /**
     * 自动释放锁时间
     */
    public final static int STUDY_PLAN_FAILURE_TIME = 6000;
    
    /**
     * 纳入培训计划的月份长度
     */
    public final static int STUDY_PLAN_MONTH_LEN = 11;
    
    /**
     * 操作类型:发布
     */
    public final static String OPERATION_TYPE_PUBLISH = "publish";
    
    /**
     * 操作类型:开启
     */
    public final static String OPERATION_TYPE_OPEN = "open";
    
    /**
     * 操作类型:关闭
     */
    public final static String OPERATION_TYPE_CLOSE = "close";
}
