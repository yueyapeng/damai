package com.damai.wine.dao.mapper;

import com.damai.wine.dao.model.WineHistoryPrice;
import com.damai.wine.dto.request.price.WineHistoryPriceQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WineHistoryPriceMapper {

    int insertSelective(WineHistoryPrice record);

    /**
     * 根据主键id 删除商品的历史价格
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键id 查询商品的历史价格
     * @param id
     * @return
     */
    WineHistoryPrice selectByPrimaryKey(String id);

    /**
     * 根据商品的 WineRealtimePriceId 查询商品的历史价格记录（实时价格记录会被删除转移至历史记录中）
     * @param wineProductId
     * @return
     */
    WineHistoryPrice selectByWineRealtimePriceId(String wineProductId);

    /**
     * 根据复合条件查询历史价格记录
     * @param record
     * @return
     */
    List<WineHistoryPrice> selectByExample(WineHistoryPriceQueryDto record);
}