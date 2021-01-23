package com.damai.wine.api.service;

import com.damai.wine.api.common.Result;
import com.damai.wine.api.service.request.order.WineOrderAddRequest;
import com.damai.wine.api.service.request.order.WineOrderQueryRequest;
import com.damai.wine.api.service.request.order.WineOrderUpdateRequest;
import com.damai.wine.api.service.response.order.WineOrderDto;

import java.util.List;

public interface WineOrderRpcService {

    /**
     * 订单新增
     * @param request
     * @return
     */
    Result<WineOrderDto> addWineOrder(WineOrderAddRequest request);

    /**
     * 根据订单主键 id 查询订单信息
     * @param id
     * @return
     */
    Result<WineOrderDto> queryWineOrderById(String id);

    /**
     * 根据复合条件查询订单信息
     * @param request
     * @return
     */
    Result<List<WineOrderDto>> queryWineOrderByExample(WineOrderQueryRequest request);

    /**
     * 根据订单主键 id 更新订单信息
     * @param request
     * @return
     */
    Result<WineOrderDto> updateWineOrderById(WineOrderUpdateRequest request);

}
