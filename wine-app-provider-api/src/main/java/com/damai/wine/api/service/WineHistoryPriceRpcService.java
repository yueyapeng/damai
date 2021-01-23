package com.damai.wine.api.service;

import com.damai.wine.api.common.Result;
import com.damai.wine.api.service.request.price.WineHistoryPriceQueryRequest;
import com.damai.wine.api.service.response.price.WineHistoryPriceDto;

import java.util.List;

public interface WineHistoryPriceRpcService {

    /**
     * 根据历史价格记录主键 id 查询历史价格信息
     * @param id
     * @return
     */
    Result<WineHistoryPriceDto> queryWineHistoryPriceById(String id);

    /**
     * 根据实时价格的主键 id 查询历史价格信息
     * @param wineRealtimePriceId
     * @return
     */
    Result<WineHistoryPriceDto> queryWineHistoryPriceByWineRealtimePriceId(String wineRealtimePriceId);

    /**
     * 根据复合条件查询历史价格信息
     * @param request
     * @return
     */
    Result<List<WineHistoryPriceDto>> queryWineHistoryPriceByExample(WineHistoryPriceQueryRequest request);

}
