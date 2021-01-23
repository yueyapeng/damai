package com.damai.wine.api.service.request.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class WineProductQueryRequest implements Serializable {

    private static final long serialVersionUID = -4389828825414600511L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 酒水类型(茅台、五粮液)
     */
    private String wineType;

    /**
     * 酒水系列(飞天、生肖系列)
     */
    private String wineBrand;

    /**
     * 生产年份
     */
    private String productionYear;

    /**
     * 酒水度数
     */
    private String degree;

    /**
     * 酒容量（500ml、1000ml）
     */
    private String capacity;

    /**
     * 生肖
     */
    private String chineseZodiac;

    /**
     * 包装(1:散装、2:原箱)
     */
    private String packaging;

    /**
     * 如果packaging=2 原箱包装，每箱的瓶数
     */
    private String bottleNumber;

    /**
     * 当前产品状态（Y：上架、N：已下架）
     */
    private String status;

    private String startTime;

    private String endTime;

}