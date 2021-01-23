package com.damai.wine.api.common.enums;

/**
 * 附件的数据来源
 */
public enum AttachmentDataSourceEnum {
    WECHAT_MINI_PROGRAM("1", "微信小程序"),
    WEB("2", "运营管理后台");

    private String value;
    private String desc;

    AttachmentDataSourceEnum(String value, String desc) {
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
