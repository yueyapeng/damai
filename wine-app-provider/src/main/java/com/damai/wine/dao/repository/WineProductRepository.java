package com.damai.wine.dao.repository;

import com.damai.wine.dao.mapper.WineProductMapper;
import com.damai.wine.dao.model.WineProduct;
import com.damai.wine.dto.request.product.WineProductQueryDto;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class WineProductRepository {

    @Resource
    private WineProductMapper wineProductMapper;

    /**
     * 新增酒类产品信息
     * @param record
     */
    public void addWineProduct(WineProduct record) {
        wineProductMapper.insertSelective(record);
    }

    /**
     * 根据产品主键 id 查询产品信息
     * @param id
     * @return
     */
    public WineProduct queryByPrimaryKey(String id) {
        return wineProductMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据产品要素条件查询产品信息
     * @param record
     * @return
     */
    public List<WineProduct> queryByExample(WineProductQueryDto record) {
        return wineProductMapper.selectByExample(record);
    }

    /**
     * 根据产品的主键 id 更新产品信息
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(WineProduct record){
        return wineProductMapper.updateByPrimaryKeySelective(record);
    }

}
