package com.damai.wine.api.service.response.product;

import com.damai.wine.api.service.response.BaseResponse;
import lombok.Data;

import java.util.Date;

/**
 * 产品查询返回对象
 */
@Data
public class WineProductDto extends BaseResponse {

    private static final long serialVersionUID = 1371109790318650738L;
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

    private Date createTime;

    private Date updateTime;


}