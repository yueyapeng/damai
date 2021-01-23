package com.damai.wine.dao.mapper;

import com.damai.wine.dao.model.WineProduct;
import com.damai.wine.dto.request.product.WineProductQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WineProductMapper{

    int insert(WineProduct record);

    int insertSelective(WineProduct record);


    /**
     * 根据主键id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键id查询酒产品信息
     * @param id
     * @return
     */
    WineProduct selectByPrimaryKey(String id);

    /**
     * 根据复合条件查询对应的酒产品信息
     * @param queryExample
     * @return
     */
    List<WineProduct> selectByExample(WineProductQueryDto queryExample);

    /**
     * 根据产品的主键 id 更新产品信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WineProduct record);

}