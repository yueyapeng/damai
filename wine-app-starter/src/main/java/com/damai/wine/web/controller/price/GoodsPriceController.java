package com.damai.wine.web.controller.price;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.ProductStatusEnum;
import com.damai.wine.api.service.response.price.WineRealtimePriceDto;
import com.damai.wine.common.AppResult;
import com.damai.wine.common.AppWrapResult;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.wrapper.price.GoodsPriceServiceWrapper;
import com.damai.wine.web.controller.BaseController;
import com.damai.wine.web.controller.price.request.GoodsPriceQueryRequest;
import com.damai.wine.web.controller.price.response.GoodsPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/price")
public class GoodsPriceController extends BaseController {

    @Autowired
    GoodsPriceServiceWrapper goodsPriceServiceWrapper;

    @ResponseBody
    @RequestMapping("/getGoodsPrice ")
    public AppResult<GoodsPriceResponse> getGoodsPrice(GoodsPriceQueryRequest request) {
        String traceId = getTraceId();
        AppResult result = AppWrapResult.success();
        try {
            if (log.isInfoEnabled()){
                log.info("[getGoodsPrice]请求参数:request{}", JSONObject.toJSONString(request));
            }
            // 默认查询当前上架的产品信息
            request.setStatus(ProductStatusEnum.Y.getValue());
            GoodsPriceResponse goodsPriceDto = goodsPriceServiceWrapper.getWineProductPrice(request);
            if (goodsPriceDto != null){
                result.setData(goodsPriceDto);
            }
            return result;
        }  catch (WineException error) {
            log.error("[getUserInfo]traceId:{},获取用户开发数据失败,{}", traceId, JSONObject.toJSONString(error));
            result.setRetCode(error.getRetCode());
            result.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[getUserInfo]traceId:{},获取用户开放数据异常", traceId, e);
            result.setRetCode(ResponseResultCode.FAILED.getCode());
            result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }



}
