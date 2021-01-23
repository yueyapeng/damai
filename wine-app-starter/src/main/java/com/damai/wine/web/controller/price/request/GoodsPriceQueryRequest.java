package com.damai.wine.web.controller.price.request;

import com.damai.wine.api.common.enums.ChineseZodiacEnum;
import com.damai.wine.api.common.enums.ProductStatusEnum;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.api.common.enums.WineTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsPriceQueryRequest implements Serializable {

    private static final long serialVersionUID = -7623694821101866461L;

    /**
     * @see WineTypeEnum
     * maotai/wuliangye
     */
    private String wineType;

    /**
     * @see WineBrandEnum
     */
    private String wineBrand;

    /**
     * 生产年份 如：2019、2020
     */
    private String productionYear;

    /**
     * 酒水度数 53、52
     */
    private String degree;

    /**
     * 容量ml 如：500、1000、
     */
    private String capacity;

    /**
     * @see ChineseZodiacEnum
     * 具体生肖
     */
    private String chineseZodiac;

    /**
     * 包装(1:散装、2:原箱)
     */
    private String packaging;

    /**
     * 当前产品状态 是否上架  Y：上架、N：下架
     * @see ProductStatusEnum
     */
    private String status;

}
