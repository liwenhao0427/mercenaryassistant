package com.mercbuddy.mercenaryassistant.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 键
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {

    //是否为主键
    boolean isPK() default false;
    //对应的数据库的字段名，默认为空
    String label() default "";

}