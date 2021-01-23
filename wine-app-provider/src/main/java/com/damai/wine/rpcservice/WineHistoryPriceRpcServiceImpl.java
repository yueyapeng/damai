package com.damai.wine.rpcservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.Result;
import com.damai.wine.api.common.WrapResult;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.service.WineHistoryPriceRpcService;
import com.damai.wine.api.service.request.price.WineHistoryPriceQueryRequest;
import com.damai.wine.api.service.response.price.WineHistoryPriceDto;
import com.damai.wine.service.price.WineHistoryPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 提供对外的dubbo服务
 */
@Slf4j
@Service
public class WineHistoryPriceRpcServiceImpl extends BaseService implements WineHistoryPriceRpcService {

    @Autowired
    WineHistoryPriceService wineHistoryPriceService;

    @Override
    public Result<WineHistoryPriceDto> queryWineHistoryPriceById(String id) {
        if (log.isInfoEnabled()){
            log.info("[queryWineHistoryPriceById] 请求参数 id:{}", id);
        }
        Result<WineHistoryPriceDto> result = WrapResult.success();
        try {
            Assert.notNull(id, "id is null");
            WineHistoryPriceDto queryResult = wineHistoryPriceService.queryWineHistoryPriceById(id);
            if (queryResult != null){
                result.setData(queryResult);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineHistoryPriceById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineHistoryPriceById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<WineHistoryPriceDto> queryWineHistoryPriceByWineRealtimePriceId(String wineRealtimePriceId) {
        if (log.isInfoEnabled()){
            log.info("[queryWineHistoryPriceByWineRealtimePriceId] 请求参数 wineRealtimePriceId:{}", wineRealtimePriceId);
        }
        Result<WineHistoryPriceDto> result = WrapResult.success();
        try {
            Assert.notNull(wineRealtimePriceId, "wineRealtimePriceId is null");
            WineHistoryPriceDto queryResult = wineHistoryPriceService.queryWineHistoryPriceByWineRealtimePriceId(wineRealtimePriceId);
            if (queryResult != null){
                result.setData(queryResult);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineHistoryPriceByWineRealtimePriceId] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineHistoryPriceByWineRealtimePriceId] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<List<WineHistoryPriceDto>> queryWineHistoryPriceByExample(WineHistoryPriceQueryRequest request) {
        if (log.isInfoEnabled()){
            log.info("[queryWineHistoryPriceByExample] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<List<WineHistoryPriceDto>> result = WrapResult.success();;
        try {
            List<WineHistoryPriceDto> lists = wineHistoryPriceService.queryWineHistoryPriceByExample(request);
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

}
