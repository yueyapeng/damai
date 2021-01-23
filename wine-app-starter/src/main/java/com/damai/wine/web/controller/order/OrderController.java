package com.damai.wine.web.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.ExpressTypeEnum;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.common.AppResult;
import com.damai.wine.common.AppWrapResult;
import com.damai.wine.common.ContextDataHolder;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.domain.UserInfo;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.wrapper.order.OrderServiceWrapper;
import com.damai.wine.web.controller.BaseController;
import com.damai.wine.web.controller.order.request.OrderQueryRequest;
import com.damai.wine.web.controller.order.request.PostGoodsRequest;
import com.damai.wine.web.controller.order.request.SubmitOrderRequest;
import com.damai.wine.web.controller.order.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    OrderServiceWrapper orderServiceWrapper;

    @ResponseBody
    @RequestMapping("/submitOrder")
    public AppResult<String> submitOrder(SubmitOrderRequest request) {
        String traceId = getTraceId();
        if (log.isInfoEnabled()){
            log.info("[submitOrder]traceId:{},请求参数:request{}", traceId, JSONObject.toJSONString(request));
        }
        AppResult result = AppWrapResult.success();
        try {
            // 1.参数校验
            validateSubmitOrderParam(request);

            // 缓存获取用户信息，设置 userId
            UserInfo userInfo = ContextDataHolder.getInstance().getUserInfo();
            request.setUserId(userInfo.getId());

            // 如果请求中手机号为空，默认将微信用户信息中的手机号设置进来
            if (StringUtils.isBlank(request.getPhone())){
                request.setPhone(userInfo.getPhone());
            }

            String orderId = orderServiceWrapper.submitOrder(request);
            result.setData(orderId);
            return result;
        }  catch (WineException error) {
            log.error("[submitOrder]traceId:{},提价订单数据失败,{}", traceId, JSONObject.toJSONString(error));
            result.setRetCode(error.getRetCode());
            result.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[submitOrder]traceId:{},提交订单数据异常", traceId, e);
            result.setRetCode(ResponseResultCode.FAILED.getCode());
            result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }

    private void validateSubmitOrderParam(SubmitOrderRequest request){
        try {
            Assert.notNull(request, "SubmitOrderRequest is null");
            Assert.notNull(request.getWineProductId(), "wineProductId is null");
            Assert.notNull(request.getWineRealtimePriceId(), "wineRealtimePriceId is null");
            Assert.notNull(request.getWineType(), "wineType is null");
            Assert.notNull(request.getWineBrand(), "wineBrand is null");
            if (request.getWineBrand().equals(WineBrandEnum.SHENG_XIAO)){
                Assert.notNull(request.getChineseZodiac(), "chineseZodiac is null");
            }else {
                Assert.notNull(request.getProductionYear(), "productionYear is null");
            }
            Assert.notNull(request.getPrice(), "price is null");
            Assert.notNull(request.getBottleNumber(), "bottleNumber is null");
            Assert.notNull(request.getTotalAmount(), "totalAmount is null");
            BigDecimal calculateTotalAomount = new BigDecimal(request.getPrice()).multiply(new BigDecimal(request.getBottleNumber()));
            if (calculateTotalAomount.compareTo(new BigDecimal(request.getTotalAmount())) != 0){
                log.error("计算金额不一致。price:{},bottleNumber:{},totalAmount:{}", request.getPrice(), request.getBottleNumber(), request.getTotalAmount());
                throw new IllegalArgumentException("商品单价乘以数量与总金额不一致");
            }
            Assert.notNull(request.getColType(), "colType is null");
            Assert.notNull(request.getColAccount(), "colAccount is null");
            Assert.notNull(request.getColName(), "colName is null");
        }catch (Exception e){
            log.error("[validateSubmitOrderParam] 参数校验失败", e);
            throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * 邮寄商品
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/postGoods")
    public AppResult<String> postGoods(PostGoodsRequest request) {
        String traceId = getTraceId();
        if (log.isInfoEnabled()){
            log.info("[postGoods]traceId:{},请求参数:request{}", traceId, JSONObject.toJSONString(request));
        }
        AppResult result = AppWrapResult.success();
        try {
            // 1.参数校验
            validatePostGoodsParam(request);

            // 缓存获取用户信息，校验用户信息
            UserInfo userInfo = ContextDataHolder.getInstance().getUserInfo();
            OrderResponse order = orderServiceWrapper.queryWineOrderDetail(request.getId(), false);
            if (!order.getUserId().equals(userInfo.getId())){
                throw new IllegalArgumentException("当前操作用户信息有误");
            }

            orderServiceWrapper.postGoods(request);

            return result;
        }  catch (WineException error) {
            log.error("[submitOrder]traceId:{},提价订单数据失败,{}", traceId, JSONObject.toJSONString(error));
            result.setRetCode(error.getRetCode());
            result.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[submitOrder]traceId:{},提交订单数据异常", traceId, e);
            result.setRetCode(ResponseResultCode.FAILED.getCode());
            result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }

    private void validatePostGoodsParam(PostGoodsRequest request){
        try {
            Assert.notNull(request, "PostGoodsRequest is null");
            Assert.notNull(request.getId(), "id is null");
            Assert.notNull(request.getWineProductId(), "wineProductId is null");
            Assert.notNull(request.getExpressType(), "expressType is null");
            if (ExpressTypeEnum.EXPRESS.getValue().equals(request.getExpressType())){
                Assert.notNull(request.getExpressBillNo(), "expressBillNo is null");
            }
        }catch (Exception e){
            log.error("[validatePostGoodsParam] 参数校验失败", e);
            throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping("/getOrders")
    public AppResult<List<OrderResponse>> getOrders(OrderQueryRequest request) {
        String traceId = getTraceId();
        AppResult<List<OrderResponse>> result = AppWrapResult.success();
        try {
            if (log.isInfoEnabled()){
                log.info("[getOrders]请求参数:request{}", JSONObject.toJSONString(request));
            }

            // 缓存获取用户信息，设置 userId
            UserInfo userInfo = ContextDataHolder.getInstance().getUserInfo();
            request.setUserId(userInfo.getId());

            List<OrderResponse> resultList = orderServiceWrapper.queryWineOrders(request);
            if (resultList != null){
                result.setData(resultList);
            }
            return result;
        }  catch (WineException error) {
            log.error("[getOrders]traceId:{},查询订单数据失败,{}", traceId, JSONObject.toJSONString(error));
            result.setRetCode(error.getRetCode());
            result.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[getOrders]traceId:{},查询订单数据异常", traceId, e);
            result.setRetCode(ResponseResultCode.FAILED.getCode());
            result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrderDetail")
    public AppResult<OrderResponse> getOrderDetail(String id) {
        String traceId = getTraceId();
        AppResult result = AppWrapResult.success();
        try {
            if (log.isInfoEnabled()){
                log.info("[getOrderDetail]请求参数:id:{}", id);
            }
            OrderResponse order = orderServiceWrapper.queryWineOrderDetail(id, true);
            result.setData(order);
            return result;
        }  catch (WineException error) {
            log.error("[getOrderDetail]traceId:{},查询订单详情数据失败,{}", traceId, JSONObject.toJSONString(error));
            result.setRetCode(error.getRetCode());
            result.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[getOrderDetail]traceId:{},查询订单详情数据异常", traceId, e);
            result.setRetCode(ResponseResultCode.FAILED.getCode());
            result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }

}
