package com.damai.wine.web.controller.order.request;

import com.damai.wine.api.common.enums.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 邮寄商品请求
 */
@Data
public class PostGoodsRequest implements Serializable {


    private String id;

    /**
     * 当前订单对应的产品id
     */
    private String wineProductId;

    /**
     * 邮寄快递方式：1：快递公司、2：线下取件
     * @see ExpressTypeEnum
     */
    private String expressType;

    /**
     * 快递单号
     */
    private String expressBillNo;

}
