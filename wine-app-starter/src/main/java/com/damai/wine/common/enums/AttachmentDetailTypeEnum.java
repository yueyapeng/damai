package com.damai.wine.common.enums;


import java.io.Serializable;

/**
 * 某一类附件类型的详细分类
 */
public enum AttachmentDetailTypeEnum implements Serializable {


    /**
     * 商品图片
     */
    BOTTLE_BODY_POSITIVE("1", "瓶身正面"),
    BOTTLE_BODY_OBVERSE("2", "瓶身反面"),
    BOTTLE_CAP_CODE("3", "瓶盖喷码"),


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
    AttachmentDetailTypeEnum(String value, String desc) {
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
    public static AttachmentDetailTypeEnum parse(String i) {
        for (AttachmentDetailTypeEnum attachmentType : AttachmentDetailTypeEnum.values()) {
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
        for (AttachmentDetailTypeEnum attachmentType : AttachmentDetailTypeEnum.values()) {
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
    public static boolean contains(AttachmentDetailTypeEnum value) {
        for (AttachmentDetailTypeEnum attachmentType : AttachmentDetailTypeEnum.values()) {
            if (attachmentType.equals(value)) {
                return true;
            }
        }
        return false;
    }


}
