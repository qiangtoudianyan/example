package com.zzl.common.annotations;

import com.boco.mis.common.enums.OperationTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 张志龙
 * @Date: 2018/8/19 22:28
 * @Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationType{
    OperationTypeEnum value();
}
