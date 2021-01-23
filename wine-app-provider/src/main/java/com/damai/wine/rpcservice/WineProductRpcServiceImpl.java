package com.damai.wine.rpcservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.Result;
import com.damai.wine.api.common.WrapResult;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.api.service.WineProductRpcService;
import com.damai.wine.api.service.request.product.WineProductAddRequest;
import com.damai.wine.api.service.request.product.WineProductQueryRequest;
import com.damai.wine.api.service.request.product.WineProductUpdateRequest;
import com.damai.wine.api.service.response.product.WineProductDto;
import com.damai.wine.service.product.WineProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 提供对外的dubbo服务
 */
@Slf4j
@Service
public class WineProductRpcServiceImpl extends BaseService implements WineProductRpcService {

    @Autowired
    WineProductService wineProductService;

    @Override
    public Result<WineProductDto> addWineProduct(WineProductAddRequest request) {

        if (log.isInfoEnabled()){
            log.info("[addWineProduct] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<WineProductDto> result = WrapResult.success();
        try {
            validateCreateWineProduct(request);
            String id = wineProductService.insertWineProduct(request);
            request.setId(id);
            result.setData(copyProperties(request, WineProductDto.class));
        }catch (IllegalArgumentException e){
            log.error("[addWineProduct] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[addWineProduct] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<WineProductDto> queryWineProductById(String id) {
        if (log.isInfoEnabled()){
            log.info("[queryWineProductById] 请求参数 id:{}", id);
        }
        Result<WineProductDto> result = WrapResult.success();
        try {
            Assert.notNull(id, "id is null");
            WineProductDto wineProductDto = wineProductService.queryWineProductById(id);
            if (wineProductDto != null){
                result.setData(wineProductDto);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineProductById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineProductById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<List<WineProductDto>> queryWineProductByExample(WineProductQueryRequest request) {
        if (log.isInfoEnabled()){
            log.info("[queryWineProductByExample] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<List<WineProductDto>> result = WrapResult.success();
        try {
            List<WineProductDto> lists = wineProductService.queryWineProductByExample(request);
            if (lists != null){
                result.setData(lists);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryWineProductByExample] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryWineProductByExample] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<WineProductDto> updateWineProductById(WineProductUpdateRequest request) {
        if (log.isInfoEnabled()){
            log.info("[updateWineProductById] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<WineProductDto> result = WrapResult.success();
        try {
            Assert.notNull(request, "WineProductUpdateRequest is null");
            Assert.notNull(request.getId(), "id is null");
            wineProductService.updateWineProductById(request);
            result.setData(copyProperties(request, WineProductDto.class));
        }catch (IllegalArgumentException e){
            log.error("[updateWineProductById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[updateWineProductById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    private void validateCreateWineProduct(WineProductAddRequest request){
        Assert.notNull(request, "WineProductAddRequest is null");
        Assert.notNull(request.getWineType(), "wineType is null");
        Assert.notNull(request.getWineBrand(), "wineBrand is null");
        Assert.notNull(request.getDegree(), "degree is null");
        Assert.notNull(request.getCapacity(), "capacity is null");
        if (request.getWineBrand().equals(WineBrandEnum.SHENG_XIAO)){
            Assert.notNull(request.getChineseZodiac(), "chineseZodiac is null");
        }else {
            Assert.notNull(request.getProductionYear(), "productionYear is null");
        }
        // 包装(1:散装、2:原箱)
        Assert.notNull(request.getPackaging(), "packaging is null");
        // 如果packaging=1 散装的瓶数、 如果packaging=2 原箱包装，每箱的瓶数
        Assert.notNull(request.getBottleNumber(), "bottleNumber is null");
    }
}
