package com.damai.wine.api.common.enums;

/**
 * 产品状态
 * Created by yueyp on 2020/11/3.
 */
public enum ProductStatusEnum {
    Y("Y", "上架"),
    N("N", "下架");

    private String value;
    private String desc;

    ProductStatusEnum(String value, String desc) {
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
