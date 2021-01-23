package com.damai.wine.common;

public class AppWrapResult {

    public AppWrapResult() {
    }

    public static <T> AppResult<T> success() {
        AppResult<T> result = new AppResult();
        result.setRetCode(ResponseResultCode.SUCCESS.getCode());
        result.setRetMsg(ResponseResultCode.SUCCESS.getMsg());
        return result;
    }

    public static <T> AppResult<T> success(T data) {
        AppResult<T> result = new AppResult();
        result.setData(data);
        result.setRetCode(ResponseResultCode.SUCCESS.getCode());
        result.setRetMsg(ResponseResultCode.SUCCESS.getMsg());
        return result;
    }

    public static <T> AppResult<T> success(T data, String msg) {
        AppResult<T> result = new AppResult();
        result.setData(data);
        result.setRetCode(ResponseResultCode.SUCCESS.getCode());
        result.setRetMsg(msg);
        return result;
    }

    public static <T> AppResult<T> error(T data) {
        AppResult<T> result = new AppResult();
        result.setRetCode(ResponseResultCode.FAILED.getCode());
        result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> AppResult<T> error(T data, String msg) {
        AppResult<T> result = new AppResult();
        result.setRetCode(ResponseResultCode.FAILED.getCode());
        result.setRetMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> AppResult<T> error(T data, String code, String msg) {
        AppResult<T> result = new AppResult();
        result.setRetCode(code);
        result.setRetMsg(msg);
        result.setData(data);
        return result;
    }

}
