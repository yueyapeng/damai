package com.damai.wine.rpcservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.Result;
import com.damai.wine.api.common.WrapResult;
import com.damai.wine.api.common.enums.OrderStatusEnum;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.api.service.WineOrderRpcService;
import com.damai.wine.api.service.request.order.WineOrderAddRequest;
import com.damai.wine.api.service.request.order.WineOrderQueryRequest;
import com.damai.wine.api.service.request.order.WineOrderUpdateRequest;
import com.damai.wine.api.service.response.order.WineOrderDto;
import com.damai.wine.service.order.WineOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提供对外的dubbo服务
 */
@Slf4j
@Service
public class WineOrderRpcServiceImpl extends BaseService implements WineOrderRpcService {

    @Autowired
    WineOrderService wineOrderService;

    @Override
    public Result<WineOrderDto> addWineOrder(WineOrderAddRequest request) {

        if (log.isInfoEnabled()){
            log.info("[addWineOrder] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<WineOrderDto> result = WrapResult.success();
        try {
            validateCreateWineOrder(request);
            String id = wineOrderService.insertWineOrder(request);
            request.setId(id);
            result.setData(copyProperties(request, WineOrderDto.class));
        }catch (IllegalArgumentException e){
            log.error("[addWineOrder] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[addWineOrder] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<WineOrderDto> queryWineOrderById(String id) {
        if (log.isInfoEnabled()){
            log.info("[queryWineOrderById] 请求参数 id:{}", id);
        }
        Result<WineOrderDto> result = WrapResult.success();
        try {
            Assert.notNull(id, "id is null");
            WineOrderDto wineOrderDto = wineOrderService.queryWineOrderById(id);
            if (wineOrderDto != null){
                result.setData(wineOrderDto);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineOrderById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineOrderById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<List<WineOrderDto>> queryWineOrderByExample(WineOrderQueryRequest request) {
        if (log.isInfoEnabled()){
            log.info("[queryWineOrderByExample] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<List<WineOrderDto>> result = WrapResult.success();
        try {
            List<WineOrderDto> lists = wineOrderService.queryWineOrderByExample(request);
            if (lists != null){
                result.setData(lists);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineOrderByExample] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineOrderByExample] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<WineOrderDto> updateWineOrderById(WineOrderUpdateRequest request) {
        if (log.isInfoEnabled()){
            log.info("[updateWineOrderById] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<WineOrderDto> result = WrapResult.success();
        try {
            Assert.notNull(request, "WineOrderUpdateRequest is null");
            Assert.notNull(request.getId(), "id is null");
            wineOrderService.updateWineOrderById(request);
            result.setData(copyProperties(request, WineOrderDto.class));
        }catch (IllegalArgumentException e){
            log.error("[updateWineOrderById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[updateWineOrderById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }


    private void validateCreateWineOrder(WineOrderAddRequest request){
        Assert.notNull(request, "WineOrderAddRequest is null");
        Assert.notNull(request.getWineProductId(), "wineProductId is null");
        Assert.notNull(request.getUserId(), "userId is null");
        Assert.notNull(request.getWineRealtimePriceId(), "wineRealtimePriceId is null");
        Assert.notNull(request.getWineType(), "wineType is null");
        Assert.notNull(request.getWineBrand(), "wineBrand is null");
        if (request.getWineBrand().equals(WineBrandEnum.SHENG_XIAO)){
            Assert.notNull(request.getChineseZodiac(), "chineseZodiac is null");
        }else {
            Assert.notNull(request.getProductionYear(), "productionYear is null");
        }
        Assert.notNull(request.getPrice(), "price is null");
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

        // init status
        if (StringUtils.isBlank(request.getStatus())){
            request.setStatus(OrderStatusEnum.INIT.getValue());
        }
    }
}
