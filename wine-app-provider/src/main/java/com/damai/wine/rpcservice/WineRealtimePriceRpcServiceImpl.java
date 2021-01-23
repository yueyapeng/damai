package com.damai.wine.rpcservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.Result;
import com.damai.wine.api.common.WrapResult;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.service.WineRealtimePriceRpcService;
import com.damai.wine.api.service.request.price.WineRealtimePriceAddRequest;
import com.damai.wine.api.service.request.price.WineRealtimePriceQueryRequest;
import com.damai.wine.api.service.request.price.WineRealtimePriceUpdateRequest;
import com.damai.wine.api.service.response.price.WineRealtimePriceDto;
import com.damai.wine.service.price.WineRealtimePriceService;
import com.damai.wine.service.price.WineRealtimePriceTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 提供对外的dubbo服务
 */
@Slf4j
@Service
public class WineRealtimePriceRpcServiceImpl extends BaseService implements WineRealtimePriceRpcService {

    @Autowired
    WineRealtimePriceService wineRealtimePriceService;

    @Autowired
    WineRealtimePriceTransactionService wineRealtimePriceTransactionService;

    @Override
    public Result<WineRealtimePriceDto> addWineRealtimePrice(WineRealtimePriceAddRequest request) {

        if (log.isInfoEnabled()){
            log.info("[addWineRealtimePrice] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<WineRealtimePriceDto> result = WrapResult.success();
        try {
            validateCreateWinePrice(request);
            String id = wineRealtimePriceTransactionService.insertWinePrice(request);
            request.setId(id);
            result.setData(copyProperties(request, WineRealtimePriceDto.class));
        } catch (IllegalArgumentException e){
            log.error("[addWineRealtimePrice] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[addWineRealtimePrice] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<WineRealtimePriceDto> queryWineRealtimePriceById(String id) {
        if (log.isInfoEnabled()){
            log.info("[queryWineRealtimePriceById] 请求参数 id:{}", id);
        }
        Result<WineRealtimePriceDto> result = WrapResult.success();
        try {
            Assert.notNull(id, "id is null");
            WineRealtimePriceDto wineRealtimePriceDto = wineRealtimePriceService.queryWineRealtimePriceById(id);
            if (wineRealtimePriceDto != null){
                result.setData(wineRealtimePriceDto);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineRealtimePriceById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineRealtimePriceById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<WineRealtimePriceDto> queryWineRealtimePriceByWineProductId(String wineProductId) {
        if (log.isInfoEnabled()){
            log.info("[queryWineRealtimePriceByWineProductId] 请求参数 wineProductId:{}", wineProductId);
        }
        Result<WineRealtimePriceDto> result = WrapResult.success();
        try {
            Assert.notNull(wineProductId, "wineProductId is null");
            WineRealtimePriceDto wineRealtimePriceDto = wineRealtimePriceService.queryWineRealtimePriceByWineProductId(wineProductId);
            if (wineRealtimePriceDto != null){
                result.setData(wineRealtimePriceDto);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineRealtimePriceByWineProductId] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineRealtimePriceByWineProductId] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<List<WineRealtimePriceDto>> queryWineRealtimePriceByExample(WineRealtimePriceQueryRequest request) {
        if (log.isInfoEnabled()){
            log.info("[queryWineRealtimePriceByExample] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<List<WineRealtimePriceDto>> result = WrapResult.success();;
        try {
            List<WineRealtimePriceDto> lists = wineRealtimePriceService.queryWineRealtimePriceByExample(request);
            if (lists != null){
               result.setData(lists);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineRealtimePriceByExample] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineRealtimePriceByExample] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<WineRealtimePriceDto> updateWineRealtimePriceById(WineRealtimePriceUpdateRequest request) {
        if (log.isInfoEnabled()){
            log.info("[updateWineRealtimePriceById] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<WineRealtimePriceDto> result = WrapResult.success();
        try {
            validateUpdateWinePrice(request);
            WineRealtimePriceDto wineRealtimePriceDto = wineRealtimePriceTransactionService.updateWinePrice(request);
            if (wineRealtimePriceDto != null){
                result.setData(wineRealtimePriceDto);
            }
        }catch (IllegalArgumentException e){
            log.error("[updateWineRealtimePriceById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[updateWineRealtimePriceById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }


    private void validateCreateWinePrice(WineRealtimePriceAddRequest request){
        Assert.notNull(request, "WineRealtimePriceAddRequest is null");
        Assert.notNull(request.getWineProductId(), "wineProductId is null");
        Assert.notNull(request.getPrice(), "price is null");
        Assert.notNull(request.getOperator(), "operator is null");
        Assert.notNull(request.getChannelResource(), "channelResource is null");
    }

    /**
     * 修改酒的价格参数校验
     * @param request
     */
    private void validateUpdateWinePrice(WineRealtimePriceUpdateRequest request){
        Assert.notNull(request, "WineRealtimePriceUpdateRequest is null");
        Assert.notNull(request.getId(), "id is null");
        Assert.notNull(request.getPrice(), "price is null");
        Assert.notNull(request.getOperator(), "operator is null");
        Assert.notNull(request.getChannelResource(), "channelResource is null");
    }


}
