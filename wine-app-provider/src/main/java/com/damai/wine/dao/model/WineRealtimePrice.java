package com.damai.wine.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_wine_realtime_price")
public class WineRealtimePrice {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 酒类产品的id（同一酒产品只能有一条价格记录）
     */
    @Column(name = "wine_product_id")
    private String wineProductId;

    /**
     * 当前酒价格（单位元，不支持小数）
     */
    private String price;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 价格渠道来源
     */
    @Column(name = "channel_resource")
    private String channelResource;

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
     * 获取酒类产品的id（同一酒产品只能有一条价格记录）
     *
     * @return wine_product_id - 酒类产品的id（同一酒产品只能有一条价格记录）
     */
    public String getWineProductId() {
        return wineProductId;
    }

    /**
     * 设置酒类产品的id（同一酒产品只能有一条价格记录）
     *
     * @param wineProductId 酒类产品的id（同一酒产品只能有一条价格记录）
     */
    public void setWineProductId(String wineProductId) {
        this.wineProductId = wineProductId;
    }

    /**
     * 获取当前酒价格（单位元，不支持小数）
     *
     * @return price - 当前酒价格（单位元，不支持小数）
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置当前酒价格（单位元，不支持小数）
     *
     * @param price 当前酒价格（单位元，不支持小数）
     */
    public void setPrice(String price) {
        this.price = price;
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
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取价格渠道来源
     *
     * @return channel_resource - 价格渠道来源
     */
    public String getChannelResource() {
        return channelResource;
    }

    /**
     * 设置价格渠道来源
     *
     * @param channelResource 价格渠道来源
     */
    public void setChannelResource(String channelResource) {
        this.channelResource = channelResource;
    }
}