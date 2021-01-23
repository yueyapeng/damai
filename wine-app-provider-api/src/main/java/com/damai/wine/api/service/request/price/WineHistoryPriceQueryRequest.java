package com.damai.wine.api.service.request.price;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 历史价格记录查询请求对象
 */
@Data
public class WineHistoryPriceQueryRequest implements Serializable {

    private static final long serialVersionUID = 6864859644446744177L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 实时价格id
     */
    private String wineRealtimePriceId;

    /**
     * 酒类产品的id（同一酒产品只能有一条价格记录）
     */
    private String wineProductId;

    /**
     * 当前酒价格（单位元，不支持小数）
     */
    private String price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建的日期（yyyy-mm-dd）
     */
    private String createDay;

    /**
     * 价格渠道来源
     */
    private String channelResource;

    private String startTime;

    private String endTime;

}