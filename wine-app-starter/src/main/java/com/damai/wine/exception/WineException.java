package com.damai.wine.exception;


import com.damai.wine.common.ResponseResultCode;

/**
 *
 */
public class WineException extends RuntimeException{

    private String retCode;

    private String retMsg;

    public WineException() {
        super();
    }

    public WineException(ResponseResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public WineException(String retCode, String retMsg){
        super(retMsg);
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public WineException(String message, Throwable cause,
                         boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WineException(String message, Throwable cause) {
        super(message, cause);
    }

    public WineException(String message) {
        super(message);
    }

    public WineException(Throwable cause) {
        super(cause);
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
}
