package com.damai.wine.api.common;

import com.damai.wine.api.common.enums.ResultCode;

import java.io.Serializable;

public class WrapResult implements Serializable {

    private static final long serialVersionUID = 2387778771865111789L;

    public WrapResult() {
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result();
        result.setRetCode(ResultCode.SUCCESS.getCode());
        result.setRetMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result();
        result.setData(data);
        result.setRetCode(ResultCode.SUCCESS.getCode());
        result.setRetMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = new Result();
        result.setData(data);
        result.setRetCode(ResultCode.SUCCESS.getCode());
        result.setRetMsg(msg);
        return result;
    }

    public static <T> Result<T> error(T data) {
        Result<T> result = new Result();
        result.setRetCode(ResultCode.FAILED.getCode());
        result.setRetMsg(ResultCode.FAILED.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(T data, String msg) {
        Result<T> result = new Result();
        result.setRetCode(ResultCode.FAILED.getCode());
        result.setRetMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(T data, String code, String msg) {
        Result<T> result = new Result();
        result.setRetCode(code);
        result.setRetMsg(msg);
        result.setData(data);
        return result;
    }

}
