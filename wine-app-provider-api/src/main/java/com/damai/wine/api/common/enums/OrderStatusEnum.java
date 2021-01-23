package com.damai.wine.api.common.enums;

/**
 * 订单状态
 * Created by yueyp on 2020/11/3.
 */
public enum OrderStatusEnum {
    INIT("init", "下单初始化"),
    POSTING("posting", "邮寄中"),
    RECEIPT("receipt", "已收件"), // 已收件（相当于是待鉴定：后续鉴定可能会有发货操作）
    IDENTIFYING("identifying", "鉴定中"), // 货物发往鉴定目的地，是鉴定中
    IDENTIFIED("identified", "已鉴定"),// 得到鉴定结果后，修改订单为已鉴定（鉴定后相当于是等待确认金额，可能订单与实际的货物有差异，议价过程。还有邮递费用的返还）
    CONFIRMING("confirming", "金额确定中"), // 鉴定结果为真时：鉴定审核页面需要传递是否有异议标识，有该标识，修改为金额确定中。无标识直接生成支付单（后续走支付流程）
    CONFIRMED("confirmed", "金额已确认"), // 金额与用户商议后，运营后台填写具体的真是金额，以及备注，点击确认。创建支付单（后续走支付流程）
    PAYING("paying", "支付中"), // 支付单创建成功，修改为支付中
    SUCCESS("success", "支付成功"), // 终态
    FAILED("failed", "支付失败"), //
    FAKE("fake", "假货"), // 终态：鉴定结果为假（后续可能会退货，也可能不退货）
    BACK("back", "退货"), // 终态：存在价格异议的，客户不同意的，进行退货处理。
    CLOSE("close", "订单关闭"), // 终态：订单创建后，一定时间内未发货，关闭订单
    ;

    private String value;
    private String desc;

    OrderStatusEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String name) {
        this.desc = name;
    }
}
