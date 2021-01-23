package com.damai.wine.web.controller.attachment.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadAttachmentRequest implements Serializable {

    private static final long serialVersionUID = 9113091028823435354L;
    /**
     * 订单的主键 id
     */
    private String relationId;

    /**
     * 图片字节流
     */
    private byte[] bytes;

    /**
     * 1：商品图片 2：快递图片
     */
    private String attachmentType;

    private String attachmentName;

    /**
     * 1：瓶身正面 2：瓶身反面 3：瓶盖喷码
     */
    private String attachmentDetailType;

    /**
     * 附件具体地址
     */
    private String attachmentUrl;

    /**
     * 数据来源（目前只有1：酒小程序）
     */
    private String dataSource;

    /**
     * 附近的文件名
     */
    private String fileName;

}
