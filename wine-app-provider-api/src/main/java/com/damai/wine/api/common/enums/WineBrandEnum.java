package com.damai.wine.api.common.enums;

/**
 * 酒水系列分支
 * Created by yueyp on 2020/11/3.
 */
public enum WineBrandEnum {
    SHENG_XIAO("shengxiao", "生肖系列"),
    FEI_TIAN("feitian", "飞天系列");

    private String value;
    private String desc;

    WineBrandEnum(String value, String desc) {
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
