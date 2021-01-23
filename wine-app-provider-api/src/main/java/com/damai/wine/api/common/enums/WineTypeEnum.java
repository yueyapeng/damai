package com.damai.wine.api.common.enums;

/**
 * 酒水种类
 * Created by yueyp on 2020/11/3.
 */
public enum WineTypeEnum {
    MAO_TAI("maotai", "茅台"),
    WU_LIANG_YE("wuliangye", "五粮液");

    private String value;
    private String desc;

    WineTypeEnum(String value, String desc) {
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
