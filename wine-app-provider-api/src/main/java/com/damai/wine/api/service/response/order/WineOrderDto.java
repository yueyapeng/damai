package com.damai.wine.api.service.response.order;

import com.damai.wine.api.service.response.BaseResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WineOrderDto extends BaseResponse {

    private static final long serialVersionUID = 1494012429621532691L;

    /**
     * 订单主键id
     */
    private String id;

    /**
     * 当前订单对应的产品id
     */
    private String wineProductId;

    /**
     * 用户表主键id
     */
    private String userId;

    /**
     * 提交当前产品时的价格id（对应历史价格表实时价格id）
     */
    private String wineRealtimePriceId;

    /**
     * 下单手机号
     */
    private String phone;

    /**
     * 酒水类型(1：茅台、2：五粮液)
     */
    private String wineType;

    /**
     * 某一品牌的酒水系列(飞天、生肖系列)
     */
    private String wineBrand;

    /**
     * 生产年份
     */
    private String productionYear;

    /**
     * 生肖
     */
    private String chineseZodiac;

    /**
     * 价格
     */
    private String price;

    /**
     * 商品数量
     */
    private String bottleNumber;

    /**
     * 商品总金额
     */
    private String totalAmount;

    /**
     * 订单状态（）
     */
    private String status;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 邮寄时间
     */
    private Date mailTime;

    /**
     * 收件时间
     */
    private Date receiptTime;

    /**
     * 货物鉴定时间
     */
    private Date identificationTime;

    /**
     * 打款时间
     */
    private Date payTime;

    /**
     * 退回时间
     */
    private Date backTime;

    /**
     * 订单有效截止时间
     */
    private Date validTime;

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
     * 产品鉴定结果 Y：真、N：假
     */
    private String identificationResult;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 收件人联系电话
     */
    private String receiverPhone;

    /**
     * 收件人地址
     */
    private String receiverAddress;

    /**
     * 邮寄快递方式：1：快递公司、2：线下取件
     */
    private String expressType;

    /**
     * 快递公司名称
     */
    private String expressCompany;

    /**
     * 快递单号
     */
    private String expressBillNo;

    /**
     * 快递费（后续可能会根据当前字段补客户邮费）
     */
    private String expressFee;

    /**
     * 假货退还，快递单号
     */
    private String backExpressBillNo;

}