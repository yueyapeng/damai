package com.damai.wine.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_wine_order")
public class WineOrder {
    /**
     * 订单主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 当前订单对应的产品id
     */
    @Column(name = "wine_product_id")
    private String wineProductId;

    /**
     * 用户表主键id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 提交当前产品时的价格id（对应历史价格表实时价格id）
     */
    @Column(name = "wine_realtime_price_id")
    private String wineRealtimePriceId;

    /**
     * 下单手机号
     */
    private String phone;

    /**
     * 酒水类型(1：茅台、2：五粮液)
     */
    @Column(name = "wine_type")
    private String wineType;

    /**
     * 某一品牌的酒水系列(飞天、生肖系列)
     */
    @Column(name = "wine_brand")
    private String wineBrand;

    /**
     * 生产年份
     */
    @Column(name = "production_year")
    private String productionYear;

    /**
     * 生肖
     */
    @Column(name = "chinese_zodiac")
    private String chineseZodiac;

    /**
     * 价格
     */
    private String price;

    /**
     * 商品数量
     */
    @Column(name = "bottle_number")
    private String bottleNumber;

    /**
     * 商品总金额
     */
    @Column(name = "total_amount")
    private String totalAmount;

    /**
     * 订单状态（）
     */
    private String status;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 邮寄时间
     */
    @Column(name = "mail_time")
    private Date mailTime;

    /**
     * 收件时间
     */
    @Column(name = "receipt_time")
    private Date receiptTime;

    /**
     * 货物鉴定时间
     */
    @Column(name = "identification_time")
    private Date identificationTime;

    /**
     * 打款时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 退回时间
     */
    @Column(name = "back_time")
    private Date backTime;

    /**
     * 订单有效截止时间
     */
    @Column(name = "valid_time")
    private Date validTime;

    /**
     * 收款方式（1：支付宝、2：银行卡）
     */
    @Column(name = "col_type")
    private String colType;

    /**
     * 收款账号
     */
    @Column(name = "col_account")
    private String colAccount;

    /**
     * 收款人名称
     */
    @Column(name = "col_name")
    private String colName;

    /**
     * 产品鉴定结果 Y：真、N：假
     */
    @Column(name = "identification_result")
    private String identificationResult;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 收件人联系电话
     */
    @Column(name = "receiver_phone")
    private String receiverPhone;

    /**
     * 收件人地址
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 邮寄快递方式：1：快递公司、2：线下取件
     */
    @Column(name = "express_type")
    private String expressType;

    /**
     * 快递公司名称
     */
    @Column(name = "express_company")
    private String expressCompany;

    /**
     * 快递单号
     */
    @Column(name = "express_bill_no")
    private String expressBillNo;

    /**
     * 快递费（后续可能会根据当前字段补客户邮费）
     */
    @Column(name = "express_fee")
    private String expressFee;

    /**
     * 假货退还，快递单号
     */
    @Column(name = "back_express_bill_no")
    private String backExpressBillNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取订单主键id
     *
     * @return id - 订单主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置订单主键id
     *
     * @param id 订单主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取当前订单对应的产品id
     *
     * @return wine_product_id - 当前订单对应的产品id
     */
    public String getWineProductId() {
        return wineProductId;
    }

    /**
     * 设置当前订单对应的产品id
     *
     * @param wineProductId 当前订单对应的产品id
     */
    public void setWineProductId(String wineProductId) {
        this.wineProductId = wineProductId;
    }

    /**
     * 获取用户表主键id
     *
     * @return user_id - 用户表主键id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户表主键id
     *
     * @param userId 用户表主键id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取提交当前产品时的价格id（对应历史价格表实时价格id）
     *
     * @return wine_realtime_price_id - 提交当前产品时的价格id（对应历史价格表实时价格id）
     */
    public String getWineRealtimePriceId() {
        return wineRealtimePriceId;
    }

    /**
     * 设置提交当前产品时的价格id（对应历史价格表实时价格id）
     *
     * @param wineRealtimePriceId 提交当前产品时的价格id（对应历史价格表实时价格id）
     */
    public void setWineRealtimePriceId(String wineRealtimePriceId) {
        this.wineRealtimePriceId = wineRealtimePriceId;
    }

    /**
     * 获取下单手机号
     *
     * @return phone - 下单手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置下单手机号
     *
     * @param phone 下单手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取酒水类型(1：茅台、2：五粮液)
     *
     * @return wine_type - 酒水类型(1：茅台、2：五粮液)
     */
    public String getWineType() {
        return wineType;
    }

    /**
     * 设置酒水类型(1：茅台、2：五粮液)
     *
     * @param wineType 酒水类型(1：茅台、2：五粮液)
     */
    public void setWineType(String wineType) {
        this.wineType = wineType;
    }

    /**
     * 获取某一品牌的酒水系列(飞天、生肖系列)
     *
     * @return wine_brand - 某一品牌的酒水系列(飞天、生肖系列)
     */
    public String getWineBrand() {
        return wineBrand;
    }

    /**
     * 设置某一品牌的酒水系列(飞天、生肖系列)
     *
     * @param wineBrand 某一品牌的酒水系列(飞天、生肖系列)
     */
    public void setWineBrand(String wineBrand) {
        this.wineBrand = wineBrand;
    }

    /**
     * 获取生产年份
     *
     * @return production_year - 生产年份
     */
    public String getProductionYear() {
        return productionYear;
    }

    /**
     * 设置生产年份
     *
     * @param productionYear 生产年份
     */
    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    /**
     * 获取生肖
     *
     * @return chinese_zodiac - 生肖
     */
    public String getChineseZodiac() {
        return chineseZodiac;
    }

    /**
     * 设置生肖
     *
     * @param chineseZodiac 生肖
     */
    public void setChineseZodiac(String chineseZodiac) {
        this.chineseZodiac = chineseZodiac;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(String price) {
        this.price = price;
    }

    public String getBottleNumber() {
        return bottleNumber;
    }

    public void setBottleNumber(String bottleNumber) {
        this.bottleNumber = bottleNumber;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取订单状态（）
     *
     * @return status - 订单状态（）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置订单状态（）
     *
     * @param status 订单状态（）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取邮寄时间
     *
     * @return mail_time - 邮寄时间
     */
    public Date getMailTime() {
        return mailTime;
    }

    /**
     * 设置邮寄时间
     *
     * @param mailTime 邮寄时间
     */
    public void setMailTime(Date mailTime) {
        this.mailTime = mailTime;
    }

    /**
     * 获取收件时间
     *
     * @return receipt_time - 收件时间
     */
    public Date getReceiptTime() {
        return receiptTime;
    }

    /**
     * 设置收件时间
     *
     * @param receiptTime 收件时间
     */
    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    /**
     * 获取货物鉴定时间
     *
     * @return identification_time - 货物鉴定时间
     */
    public Date getIdentificationTime() {
        return identificationTime;
    }

    /**
     * 设置货物鉴定时间
     *
     * @param identificationTime 货物鉴定时间
     */
    public void setIdentificationTime(Date identificationTime) {
        this.identificationTime = identificationTime;
    }

    /**
     * 获取打款时间
     *
     * @return pay_time - 打款时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置打款时间
     *
     * @param payTime 打款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取退回时间
     *
     * @return back_time - 退回时间
     */
    public Date getBackTime() {
        return backTime;
    }

    /**
     * 设置退回时间
     *
     * @param backTime 退回时间
     */
    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    /**
     * 获取订单有效截止时间
     *
     * @return valid_time - 订单有效截止时间
     */
    public Date getValidTime() {
        return validTime;
    }

    /**
     * 设置订单有效截止时间
     *
     * @param validTime 订单有效截止时间
     */
    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    /**
     * 获取收款方式（1：支付宝、2：银行卡）
     *
     * @return col_type - 收款方式（1：支付宝、2：银行卡）
     */
    public String getColType() {
        return colType;
    }

    /**
     * 设置收款方式（1：支付宝、2：银行卡）
     *
     * @param colType 收款方式（1：支付宝、2：银行卡）
     */
    public void setColType(String colType) {
        this.colType = colType;
    }

    /**
     * 获取收款账号
     *
     * @return col_account - 收款账号
     */
    public String getColAccount() {
        return colAccount;
    }

    /**
     * 设置收款账号
     *
     * @param colAccount 收款账号
     */
    public void setColAccount(String colAccount) {
        this.colAccount = colAccount;
    }

    /**
     * 获取收款人名称
     *
     * @return col_name - 收款人名称
     */
    public String getColName() {
        return colName;
    }

    /**
     * 设置收款人名称
     *
     * @param colName 收款人名称
     */
    public void setColName(String colName) {
        this.colName = colName;
    }

    /**
     * 获取产品鉴定结果 Y：真、N：假
     *
     * @return identification_result - 产品鉴定结果 Y：真、N：假
     */
    public String getIdentificationResult() {
        return identificationResult;
    }

    /**
     * 设置产品鉴定结果 Y：真、N：假
     *
     * @param identificationResult 产品鉴定结果 Y：真、N：假
     */
    public void setIdentificationResult(String identificationResult) {
        this.identificationResult = identificationResult;
    }

    /**
     * 获取收件人
     *
     * @return receiver - 收件人
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置收件人
     *
     * @param receiver 收件人
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取收件人联系电话
     *
     * @return receiver_phone - 收件人联系电话
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 设置收件人联系电话
     *
     * @param receiverPhone 收件人联系电话
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * 获取收件人地址
     *
     * @return receiver_address - 收件人地址
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 设置收件人地址
     *
     * @param receiverAddress 收件人地址
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    /**
     * 获取邮寄快递方式：1：快递公司、2：线下取件
     *
     * @return express_type - 邮寄快递方式：1：快递公司、2：线下取件
     */
    public String getExpressType() {
        return expressType;
    }

    /**
     * 设置邮寄快递方式：1：快递公司、2：线下取件
     *
     * @param expressType 邮寄快递方式：1：快递公司、2：线下取件
     */
    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    /**
     * 获取快递公司名称
     *
     * @return express_company - 快递公司名称
     */
    public String getExpressCompany() {
        return expressCompany;
    }

    /**
     * 设置快递公司名称
     *
     * @param expressCompany 快递公司名称
     */
    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    /**
     * 获取快递单号
     *
     * @return express_bill_no - 快递单号
     */
    public String getExpressBillNo() {
        return expressBillNo;
    }

    /**
     * 设置快递单号
     *
     * @param expressBillNo 快递单号
     */
    public void setExpressBillNo(String expressBillNo) {
        this.expressBillNo = expressBillNo;
    }

    /**
     * 获取快递费（后续可能会根据当前字段补客户邮费）
     *
     * @return express_fee - 快递费（后续可能会根据当前字段补客户邮费）
     */
    public String getExpressFee() {
        return expressFee;
    }

    /**
     * 设置快递费（后续可能会根据当前字段补客户邮费）
     *
     * @param expressFee 快递费（后续可能会根据当前字段补客户邮费）
     */
    public void setExpressFee(String expressFee) {
        this.expressFee = expressFee;
    }

    /**
     * 获取假货退还，快递单号
     *
     * @return back_express_bill_no - 假货退还，快递单号
     */
    public String getBackExpressBillNo() {
        return backExpressBillNo;
    }

    /**
     * 设置假货退还，快递单号
     *
     * @param backExpressBillNo 假货退还，快递单号
     */
    public void setBackExpressBillNo(String backExpressBillNo) {
        this.backExpressBillNo = backExpressBillNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}