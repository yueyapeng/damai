package com.damai.wine.api.service.response;

import com.damai.wine.api.common.enums.ResultCode;

import java.io.Serializable;

/**
 * 返回结果基类
 * 
 * @author yueyp
 */
public class BaseResponse implements Serializable {

    /** 序列化版本 */
	private static final long serialVersionUID = -2439783599877597924L;

    /**
     * 成功的标识
     */
    private static final String SUCCESS = "0000";

	/**
     * 返回码
     */
    private String            retCode;

    /**
     * 返回提示
     */
    private String            retMsg;
    
    public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	/**
	 * 判断返回结果成功与否的标识
	 */
	public boolean isSuccess() {
		return SUCCESS.equals(this.getRetCode());
	}
	
    public static BaseResponse buildFailedResult(String message){
		BaseResponse response = new BaseResponse();
		response.setRetCode(ResultCode.SYSTEM_ERROR.getCode());
		response.setRetMsg(message);
		return response;
	}

	public static BaseResponse buildSuccessResult(){
		BaseResponse response = new BaseResponse();
		response.setRetCode(ResultCode.SUCCESS.getCode());
		return response;
	}
}
