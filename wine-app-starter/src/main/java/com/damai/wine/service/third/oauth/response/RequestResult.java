package com.damai.wine.service.third.oauth.response;

/**
 * @author zsz
 * @version 1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2018/6/19 17:30
 */
public class RequestResult {
    private int statusCode;

    private String result;

    private String message;

    public RequestResult() {
    }

    public RequestResult(int statusCode, String result, String message) {
        this.statusCode = statusCode;
        this.result = result;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
