package com.mercbuddy.mercenaryassistant.base;

import java.text.MessageFormat;

public class ResultUtils {
    private ResultUtils(){}

    public static <T> Result<T> createSuccess(){
        return new Result<>(1, null, null);
    }
    public static <T> Result<T> createSuccess(String msg){
        return new Result<>(1, null, msg);
    }

    public static <T> Result<T> createSuccess(T data){
        return new Result<>(1, data, null);
    }

    public static <T> Result<T> createFail(String msg){
        return new Result<>(0, null, msg);
    }
    public static <T> Result<T> createFail(Integer code, String msg){
        return new Result<>(code, null, msg);
    }
    public static <T> Result<T> createResult(Integer code, String msg){
        return new Result<>(code, null, msg);
    }
    public static <T> Result<T> createFail(String msg, Object... args){
        return new Result<>(0, null, MessageFormat.format(msg, args));
    }


}
