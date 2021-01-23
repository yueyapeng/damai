package com.damai.wine.dao.mapper;

import com.damai.wine.dao.model.WineRealtimePrice;
import com.damai.wine.dto.request.price.WineRealtimePriceQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WineRealtimePriceMapper {

    int insert(WineRealtimePrice record);

    int insertSelective(WineRealtimePrice record);

    /**
     * 根据主键id 删除商品的实时价格
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据商品的id wineProductId 删除商品的实时价格
     * @param wineProductId
     * @return
     */
    int deleteByWineProductId(String wineProductId);

    /**
     * 根据主键id 查询商品的实时价格
     * @param id
     * @return
     */
    WineRealtimePrice selectByPrimaryKey(String id);

    /**
     * 根据商品的id wineProductId 查询商品的实时价格
     * @param wineProductId
     * @return
     */
    WineRealtimePrice selectByWineProductId(String wineProductId);

    /**
     * 根据复合条件查询实时价格记录
     * @param queryExample
     * @return
     */
    List<WineRealtimePrice> selectByExample(WineRealtimePriceQueryDto queryExample);


}