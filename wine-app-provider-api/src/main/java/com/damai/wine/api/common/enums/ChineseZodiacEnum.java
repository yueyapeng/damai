package com.damai.wine.api.common.enums;

/**
 * 酒水生肖系列具体值
 * Created by yueyp on 2020/11/3.
 */
public enum ChineseZodiacEnum {
    MOUSE("mouse", "鼠"),
    CATTLE("cattle", "牛"),
    TIGER("tiger", "虎"),
    RABBIT("rabbit", "兔"),
    DRAGON("dragon", "龙"),
    SNAKE("snake", "蛇"),
    HORSE("horse", "马"),
    SHEEP("sheep", "羊"),
    MONKEY("monkey", "猴"),
    CHICKEN("chicken", "鸡"),
    DOG("dog", "狗"),
    PIG("pig", "猪"),
    ;

    private String value;
    private String desc;

    ChineseZodiacEnum(String value, String desc) {
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
