package com.damai.wine.api.common.enums;

/**
 * RPC 服务层操作结果返回码
 * @author yueyp
 */
public enum ResultCode {

    SUCCESS("0000", "操作成功"),
    PARAM_FAIL("1001", "参数不能为空或格式错误"),
    DB_EXCEPTION("1002", "数据库操作异常"),
//    UPDATE_USER_ERROR("1006", "用户更新异常"),

//    PARAM_FAIL("100001", "参数不能为空或格式错误"),
//    OBJECT_NULL("100002", "请求对象不能为空"),
//    NO_DATA("100003", "查询不到数据"),
//    UNIQUE_ERROR("200001", "某些数据不符合唯一性要求"),
//    RECODE_NOT_EXIST("200002", "需要更新的记录不存在"),
//    SAVE_ERROR("200003", "数据保存失败"),
//    FORBID_OPERATION_ERROR("200004", "不允许当前操作"),
//

    BUSINESS_ERROR("9997", "业务处理异常"),
    SYSTEM_ERROR("9998", "系统异常"),
    FAILED("9999", "操作失败"),

    ;




    private String code;
    private String msg;

    ResultCode(String code, String msg) {
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
