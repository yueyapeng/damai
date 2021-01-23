package com.damai.wine.service.wrapper.price;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.service.request.product.WineProductQueryRequest;
import com.damai.wine.api.service.response.price.WineRealtimePriceDto;
import com.damai.wine.api.service.response.product.WineProductDto;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.BaseService;
import com.damai.wine.service.price.WineRealtimePriceService;
import com.damai.wine.service.product.WineProductService;
import com.damai.wine.web.controller.price.request.GoodsPriceQueryRequest;
import com.damai.wine.web.controller.price.response.GoodsPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsPriceServiceWrapper extends BaseService {

    @Autowired
    WineProductService wineProductService;

    @Autowired
    WineRealtimePriceService wineRealtimePriceService;

    /**
     * 根据产品相关条件信息，查询当前商品的价格
     * @param request
     * @return
     */
    public GoodsPriceResponse getWineProductPrice(GoodsPriceQueryRequest request){
        log.info("[getWineProductPrice],请求参数:request:{}", JSONObject.toJSONString(request));

        // 1.查询产品信息，获得 productId
        WineProductQueryRequest wineProductQueryRequest = new WineProductQueryRequest();
        BeanUtils.copyProperties(request, wineProductQueryRequest);
        List<WineProductDto> lists = wineProductService.queryWineProductByExample(wineProductQueryRequest);
        if (lists == null || lists.size() == 0){
            throw new WineException(ResponseResultCode.PRODUCT_NOT_EXIST);
        }
        if (lists.size() > 1){
            throw new WineException(ResponseResultCode.PRODUCT_EXIST_MORE);
        }

        // 2.根据productId 查询实时价格记录
        WineRealtimePriceDto result = wineRealtimePriceService.queryWineRealtimePriceByWineProductId(lists.get(0).getId());
        if (result == null){
            throw new WineException(ResponseResultCode.REALTIME_PRICE_NOT_EXIST);
        }

        return copyProperties(result, GoodsPriceResponse.class);
    }

}
