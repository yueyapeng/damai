package com.damai.wine.common;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class AppResult<T>  implements Serializable {

    private static final long serialVersionUID = -4722106909309014807L;

    protected String retCode;
    protected String retMsg;
    protected Integer total;
    protected T data;

    public AppResult() {
    }

    public AppResult(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public AppResult(ResponseResultCode resultCode){
        this.retCode = resultCode.getCode();
        this.retMsg = resultCode.getMsg();
    }

    public void setResult(ResponseResultCode resultCode, String message){
        this.retCode = resultCode.getCode();
        this.retMsg = StringUtils.isBlank(message) ? resultCode.getMsg() : message;
    }

    public void setResult(ResponseResultCode resultCode, String message, T data){
        this.retCode = resultCode.getCode();
        this.retMsg = StringUtils.isBlank(message) ? resultCode.getMsg() : message;
        this.data = data;
    }

    public void setResult(String retCode, String message, Integer total, T data){
        this.retCode = retCode;
        this.retMsg = message;
        this.total = total;
        this.data = data;
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return this.retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
