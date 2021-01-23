package com.damai.wine.api.service;

import com.damai.wine.api.common.Result;
import com.damai.wine.api.service.request.product.WineProductAddRequest;
import com.damai.wine.api.service.request.product.WineProductQueryRequest;
import com.damai.wine.api.service.request.product.WineProductUpdateRequest;
import com.damai.wine.api.service.response.product.WineProductDto;

import java.util.List;

public interface WineProductRpcService {

    /**
     * 产品新增
     * @param request
     * @return
     */
    Result<WineProductDto> addWineProduct(WineProductAddRequest request);

    /**
     * 根据产品主键 id 查询产品信息
     * @param id
     * @return
     */
    Result<WineProductDto> queryWineProductById(String id);

    /**
     * 根据复合条件查询产品信息
     * @param request
     * @return
     */
    Result<List<WineProductDto>> queryWineProductByExample(WineProductQueryRequest request);

    /**
     * 根据产品主键 id 更新产品信息
     * @param request
     * @return
     */
    Result<WineProductDto> updateWineProductById(WineProductUpdateRequest request);

}
