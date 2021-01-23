package com.damai.wine.common.enums;


import java.io.Serializable;

/**
 * 附件类型
 */
public enum AttachmentTypeEnum implements Serializable {

    /**
     *
     */
    GOODS("1", "商品图片"),
    POST("2", "快递图片"),


    /**
     * 其他 protocol
     */
    OTHER("99", "其他");
    /**
     * 值
     */
    private final String value;
    /**
     * 标题
     */
    private final String desc;

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param value String
     * @param desc String
     */
    AttachmentTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }



    /**
     * parse
     *
     * @param i String
     * @return BindType
     * InvalidValueException
     */
    public static AttachmentTypeEnum parse(String i) {
        for (AttachmentTypeEnum attachmentType : AttachmentTypeEnum.values()) {
            if (attachmentType.value.equals(i)) {
                return attachmentType;
            }
        }
        return null;
    }

    /**
     * BehaviorType
     * 判断枚举中是否含有某个值
     *
     * @param value 枚举值
     * @return 有返回 true,没有返回 false
     */
    public static boolean contains(String value) {
        for (AttachmentTypeEnum attachmentType : AttachmentTypeEnum.values()) {
            if (attachmentType.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断枚举中是否含有某个值
     *
     * @param value 枚举值
     * @return 有返回 true,没有返回 false
     */
    public static boolean contains(AttachmentTypeEnum value) {
        for (AttachmentTypeEnum attachmentType : AttachmentTypeEnum.values()) {
            if (attachmentType.equals(value)) {
                return true;
            }
        }
        return false;
    }


}
