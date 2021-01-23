package com.damai.wine.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_wine_history_price")
public class WineHistoryPrice {
    /**
     * 历史价格表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "wine_realtime_price_id")
    private String wineRealtimePriceId;

    /**
     * 酒类产品的id
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
     * 创建的日期（yyyy-mm-dd）
     */
    @Column(name = "create_day")
    private String createDay;

    /**
     * 价格渠道来源
     */
    @Column(name = "channel_resource")
    private String channelResource;

    /**
     * 获取历史价格表主键
     *
     * @return id - 历史价格表主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置历史价格表主键
     *
     * @param id 历史价格表主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return wine_realtime_price_id
     */
    public String getWineRealtimePriceId() {
        return wineRealtimePriceId;
    }

    /**
     * @param wineRealtimePriceId
     */
    public void setWineRealtimePriceId(String wineRealtimePriceId) {
        this.wineRealtimePriceId = wineRealtimePriceId;
    }

    /**
     * 获取酒类产品的id
     *
     * @return wine_product_id - 酒类产品的id
     */
    public String getWineProductId() {
        return wineProductId;
    }

    /**
     * 设置酒类产品的id
     *
     * @param wineProductId 酒类产品的id
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
     * 获取创建的日期（yyyy-mm-dd）
     *
     * @return create_day - 创建的日期（yyyy-mm-dd）
     */
    public String getCreateDay() {
        return createDay;
    }

    /**
     * 设置创建的日期（yyyy-mm-dd）
     *
     * @param createDay 创建的日期（yyyy-mm-dd）
     */
    public void setCreateDay(String createDay) {
        this.createDay = createDay;
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