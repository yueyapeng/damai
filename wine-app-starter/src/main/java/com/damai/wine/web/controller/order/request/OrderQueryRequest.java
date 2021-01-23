package com.damai.wine.web.controller.order.request;

import com.damai.wine.api.common.enums.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderQueryRequest implements Serializable {

    private static final long serialVersionUID = -4327571449393653243L;

    private String id;

    /**
     * 当前订单对应的产品id
     */
    private String wineProductId;

    private String userId;

    /**
     * 提交当前产品时的价格id（对应历史价格表中的 wineRealtimePriceId ）
     */
    private String wineRealtimePriceId;

    private String phone;

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
     * @see ChineseZodiacEnum
     * 具体生肖
     */
    private String chineseZodiac;

    /**
     * 当前订单状态
     * @see OrderStatusEnum
     */
    private String status;

    /**
     * 商品单价
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

    /**
     * 邮寄快递方式：1：快递公司、2：线下取件
     * @see ExpressTypeEnum
     */
    private String expressType;
    /**
     * 快递单号
     */
    private String expressBillNo;

    /**
     * 假货退还，快递单号
     */
    private String backExpressBillNo;

    private String startTime;

    private String endTime;

    private String timeFlag;

}
