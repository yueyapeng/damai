package com.damai.wine.dao.mapper;

import com.damai.wine.dao.model.WineOrder;
import com.damai.wine.dto.request.order.WineOrderQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WineOrderMapper{


    int insertSelective(WineOrder record);

    /**
     * 根据主键id 查询商品的实时价格
     * @param id
     * @return
     */
    WineOrder selectByPrimaryKey(String id);

    /**
     * 根据符合条件查询
     * @param queryExample
     * @return
     */
    List<WineOrder> selectByExample(WineOrderQueryDto queryExample);

    /**
     * 根据主键更新订单信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WineOrder record);
}