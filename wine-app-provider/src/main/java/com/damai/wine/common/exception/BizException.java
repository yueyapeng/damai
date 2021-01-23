package com.damai.wine.common.exception;

import com.damai.wine.api.common.enums.ResultCode;
import org.springframework.util.StringUtils;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = -4045657710626582740L;

    private String            code;

    private String            msg;

    private String            detailMessage;

    public BizException() {
        super();
    }

    public BizException(ResultCode resultCode, Throwable t) {
        super(t);
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
    
    public BizException(String message) {
        super();
        this.code = ResultCode.SYSTEM_ERROR.getCode();
        this.msg = message;
    }

    public BizException(ResultCode resultCode) {
        super();
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public BizException(String code, String message) {
        super();
        this.code = code;
        this.msg = message;
    }

    public BizException(ResultCode resultCode, String detailMessage) {
        super();
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.detailMessage = detailMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    @Override
    public String getMessage() {
        return StringUtils.isEmpty(detailMessage) ? msg : msg + ":" + detailMessage;
    }

    @Override
    public String toString() {
        StringBuffer controlExceptionStr = new StringBuffer();
        controlExceptionStr.append("[BizException]");
        controlExceptionStr.append("code:");
        controlExceptionStr.append(code);
        controlExceptionStr.append(",msg:");
        controlExceptionStr.append(msg);
        controlExceptionStr.append(",detailMessage:");
        controlExceptionStr.append(detailMessage);

        return controlExceptionStr.toString();
    }

}
