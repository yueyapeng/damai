package com.damai.wine.api.service.request.attachment;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 附件新增请求对象
 */
@Data
public class AttachmentAddRequest implements Serializable {

    private static final long serialVersionUID = 835926220875657270L;

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
