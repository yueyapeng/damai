package com.damai.wine.web.controller.order.request;

import com.damai.wine.api.common.enums.ChineseZodiacEnum;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.api.common.enums.WineTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 下单操作请求
 */
@Data
public class SubmitOrderRequest implements Serializable {

    private static final long serialVersionUID = -2107736209225301569L;

    /**
     * 当前订单对应的产品id
     */
    private String wineProductId;

    /**
     * 用户表主键id
     */
    private String userId;

    /**
     * 提交当前产品时的价格id（对应历史价格表中的 wineRealtimePriceId ）
     */
    private String wineRealtimePriceId;

    /**
     * 下单手机号
     */
    private String phone;

    /**
     * 酒水类型(maotai：茅台、wuliangye：五粮液)
     * @see WineTypeEnum
     * maotai/wuliangye
     */
    private String wineType;

    /**
     * 某一品牌的酒水系列(飞天、生肖系列)
     * @see WineBrandEnum
     */
    private String wineBrand;

    /**
     * 生产年份 如：2019、2020
     */
    private String productionYear;

    /**
     * @see ChineseZodiacEnum
     * 具体生肖
     */
    private String chineseZodiac;

    /**
     * 商品的单价
     */
    private String price;

    /**
     * 商品数量（瓶数）
     */
    private String bottleNumber;

    /**
     * 当前订单总金额（单价 * 数量）
     */
    private String totalAmount;

    /**
     * 收款方式（1：支付宝、2：银行卡）
     */
    private String colType;

    /**
     * 收款账号
     */
    private String colAccount;

    /**
     * 收款人名称
     */
    private String colName;

}
