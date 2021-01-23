package com.damai.wine.web.controller.attachment.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UploadAttachmentResponse implements Serializable {

    private static final long serialVersionUID = -1756958808422669915L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 关联id
     */
    private String relationId;

    /**
     * 附件种类（1：商品图片、2：快递图片、3：签收视频、4：鉴定视频、5：退货视频、6：退货物品图片）
     */
    private String attachmentType;

    /**
     * 附件名字
     */
    private String attachmentName;

    /**
     * 附件具体地址
     */
    private String attachmentUrl;

    /**
     * 数据来源（目前只有1：酒小程序）
     */
    private String dataSource;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标识用途，可用作同一件事情下文件区分(暂时无用)
     */
    private String mark;

}
