package com.damai.wine.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_attachment")
public class Attachment {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 关联id（如订单号）
     */
    @Column(name = "relation_id")
    private String relationId;

    /**
     * 附件种类（1：商品图片、2：快递图片、3：签收视频、4：鉴定视频、5：退货视频、6：退货物品图片）
     */
    @Column(name = "attachment_type")
    private String attachmentType;

    /**
     * 附件名字
     */
    @Column(name = "attachment_name")
    private String attachmentName;

    /**
     * 附件类型的详细类别（如：商品图片包含瓶身正面，瓶身背面，瓶盖条形码）
     */
    @Column(name = "attachment_detail_type")
    private String attachmentDetailType;

    /**
     * 附件具体地址
     */
    @Column(name = "attachment_url")
    private String attachmentUrl;

    /**
     * 数据来源（目前只有1：酒小程序）
     */
    @Column(name = "data_source")
    private String dataSource;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 标识用途，可用作同一件事情下文件区分(暂时无用)
     */
    private String mark;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取关联id（如订单号）
     *
     * @return relation_id - 关联id（如订单号）
     */
    public String getRelationId() {
        return relationId;
    }

    /**
     * 设置关联id（如订单号）
     *
     * @param relationId 关联id（如订单号）
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取附件种类（1：商品图片、2：快递图片、3：签收视频、4：鉴定视频、5：退货视频、6：退货物品图片）
     *
     * @return attachment_type - 附件种类（1：商品图片、2：快递图片、3：签收视频、4：鉴定视频、5：退货视频、6：退货物品图片）
     */
    public String getAttachmentType() {
        return attachmentType;
    }

    /**
     * 设置附件种类（1：商品图片、2：快递图片、3：签收视频、4：鉴定视频、5：退货视频、6：退货物品图片）
     *
     * @param attachmentType 附件种类（1：商品图片、2：快递图片、3：签收视频、4：鉴定视频、5：退货视频、6：退货物品图片）
     */
    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    /**
     * 获取附件名字
     *
     * @return attachment_name - 附件名字
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * 设置附件名字
     *
     * @param attachmentName 附件名字
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * 获取附件类型的详细类别（如：商品图片包含瓶身正面，瓶身背面，瓶盖条形码）
     *
     * @return attachment_detail_type - 附件类型的详细类别（如：商品图片包含瓶身正面，瓶身背面，瓶盖条形码）
     */
    public String getAttachmentDetailType() {
        return attachmentDetailType;
    }

    /**
     * 设置附件类型的详细类别（如：商品图片包含瓶身正面，瓶身背面，瓶盖条形码）
     *
     * @param attachmentDetailType 附件类型的详细类别（如：商品图片包含瓶身正面，瓶身背面，瓶盖条形码）
     */
    public void setAttachmentDetailType(String attachmentDetailType) {
        this.attachmentDetailType = attachmentDetailType;
    }

    /**
     * 获取附件具体地址
     *
     * @return attachment_url - 附件具体地址
     */
    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    /**
     * 设置附件具体地址
     *
     * @param attachmentUrl 附件具体地址
     */
    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    /**
     * 获取数据来源（目前只有1：酒小程序）
     *
     * @return data_source - 数据来源（目前只有1：酒小程序）
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置数据来源（目前只有1：酒小程序）
     *
     * @param dataSource 数据来源（目前只有1：酒小程序）
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取标识用途，可用作同一件事情下文件区分(暂时无用)
     *
     * @return mark - 标识用途，可用作同一件事情下文件区分(暂时无用)
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置标识用途，可用作同一件事情下文件区分(暂时无用)
     *
     * @param mark 标识用途，可用作同一件事情下文件区分(暂时无用)
     */
    public void setMark(String mark) {
        this.mark = mark;
    }
}