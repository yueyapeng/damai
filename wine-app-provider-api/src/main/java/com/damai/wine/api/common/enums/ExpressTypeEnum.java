package com.damai.wine.api.common.enums;

/**
 * 邮寄快递方式
 * Created by yueyp on 2020/11/3.
 */
public enum ExpressTypeEnum {
    EXPRESS("1", "快递公司"),
    OFFLINE("2", "线下取件");

    private String value;
    private String desc;

    ExpressTypeEnum(String value, String desc) {
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
