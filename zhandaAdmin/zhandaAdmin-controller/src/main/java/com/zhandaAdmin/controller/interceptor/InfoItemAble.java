package com.zhandaAdmin.controller.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by admin on 2016/7/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoItemAble {
    /**
     * 数据表名称注解，默认值为类名称
     * @return
     */
    public String formId() default "";
}