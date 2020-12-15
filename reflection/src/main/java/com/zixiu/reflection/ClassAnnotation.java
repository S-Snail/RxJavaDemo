package com.zixiu.reflection;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Snail
 * Time:  2020/12/15 2:34 PM
 * FileName:  ClassAnnotation
 * 简介：
 */
@Documented()
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.LOCAL_VARIABLE,ElementType.METHOD,ElementType.PACKAGE,ElementType.PARAMETER,ElementType.TYPE})
public @interface ClassAnnotation {

    String path();

    String desc() default  "注解描述";

}
