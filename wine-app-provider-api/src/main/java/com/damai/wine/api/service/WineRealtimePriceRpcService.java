package com.damai.wine.api.service;

import com.damai.wine.api.common.Result;
import com.damai.wine.api.service.request.price.WineRealtimePriceAddRequest;
import com.damai.wine.api.service.request.price.WineRealtimePriceQueryRequest;
import com.damai.wine.api.service.request.price.WineRealtimePriceUpdateRequest;
import com.damai.wine.api.service.response.price.WineRealtimePriceDto;

import java.util.List;

public interface WineRealtimePriceRpcService {

    /**
     * 新增实时价格记录
     * @param request
     * @return
     */
    Result<WineRealtimePriceDto> addWineRealtimePrice(WineRealtimePriceAddRequest request);

    /**
     * 根据实时价格记录主键 id 查询实时价格信息
     * @param id
     * @return
     */
    Result<WineRealtimePriceDto> queryWineRealtimePriceById(String id);

    /**
     * 根据产品的主键 id 查询实时价格信息
     * @param wineProductId
     * @return
     */
    Result<WineRealtimePriceDto> queryWineRealtimePriceByWineProductId(String wineProductId);

    /**
     * 根据复合条件查询实时价格信息
     * @param request
     * @return
     */
    Result<List<WineRealtimePriceDto>> queryWineRealtimePriceByExample(WineRealtimePriceQueryRequest request);

    /**
     * 根据实时价格主键 id 更新实时价格信息
     * @param request
     * @return
     */
    Result<WineRealtimePriceDto> updateWineRealtimePriceById(WineRealtimePriceUpdateRequest request);

}
