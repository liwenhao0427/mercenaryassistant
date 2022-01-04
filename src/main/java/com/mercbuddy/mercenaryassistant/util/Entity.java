package com.mercbuddy.mercenaryassistant.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 表
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Entity {

    //表名
    String tableName();
    //备注的标识
    String label();

}
