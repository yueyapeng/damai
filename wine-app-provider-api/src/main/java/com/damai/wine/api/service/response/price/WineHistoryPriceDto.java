package com.damai.wine.api.service.response.price;

import com.damai.wine.api.service.response.BaseResponse;
import lombok.Data;

import java.util.Date;

/**
 * 实时价格查询结果对象
 */
@Data
public class WineHistoryPriceDto extends BaseResponse {

    private static final long serialVersionUID = 8048492171065631910L;

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
     * 创建的日期（yyyy-mm-dd）
     */
    private String createDay;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 价格渠道来源
     */
    private String channelResource;

}