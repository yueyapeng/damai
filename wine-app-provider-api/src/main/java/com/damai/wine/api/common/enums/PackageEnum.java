package com.damai.wine.api.common.enums;

/**
 * 散装、原箱
 */
public enum PackageEnum {
    SINGLE("1", "散装"),
    ORIGINAL("2", "原箱");

    private String value;
    private String desc;

    PackageEnum(String value, String desc) {
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
