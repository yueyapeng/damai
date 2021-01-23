package com.damai.wine.common;

/**
 * 操作结果返回码
 * @author yueyp
 */
public enum ResponseResultCode {

    SUCCESS("0000", "操作成功"),

    USER_NOT_TOKEN("1001","token参数不能为空，请重新登录"),
    USER_NOT_LOGIN("1002", "用户未登陆或token过期，请重新登录"),
    ADD_USER_ERROR("1003", "新增用户异常"),
    PARAM_FAIL("1004", "参数不能为空或格式错误"),
    UPDATE_USER_ERROR("1005", "用户更新异常"),

    PRODUCT_NOT_EXIST("1006", "不存在相关产品信息"),
    PRODUCT_EXIST_MORE("1007", "存在多条相关产品信息"),

    REALTIME_PRICE_NOT_EXIST("1008", "不存在产品的相关价格信息"),

    OSS_SYSTEM_ERROR("1009", "OSS外部接口调用异常"),

    BUSINESS_ERROR("9997", "业务处理异常"),
    SYSTEM_ERROR("9998", "系统异常"),
    FAILED("9999", "操作失败"),

    /**
     *  微信第三方返回错误码
     * -1	系统繁忙，此时请开发者稍候再试
     * 0	请求成功
     * 40029	code 无效
     * 45011	频率限制，每个用户每分钟100次
     */
    ILLEGALITY_ARGUMENT_ERROR("3001", "请求code无效"),
    TRIES_LIMIT("3002", "频率限制，每个用户每分钟100次"),
    SYSTEM_BUSY_TRY_AGAIN_LATER("3003", "系统繁忙，请稍候再试"),
    INTERFACE_SERVICE_ERROR("3004", "外部服务异常"),

    SHA_USERINFO_ERROR("3005", "用户数据验签异常"),
    DECODE_USERINFO_ERROR("3006", "解密用户数据异常"),

    ;




    private String code;
    private String msg;

    ResponseResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
