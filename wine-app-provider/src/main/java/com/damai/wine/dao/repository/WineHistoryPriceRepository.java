package com.damai.wine.dao.repository;

import com.damai.wine.dao.mapper.WineHistoryPriceMapper;
import com.damai.wine.dao.model.WineHistoryPrice;
import com.damai.wine.dto.request.price.WineHistoryPriceQueryDto;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class WineHistoryPriceRepository {

    @Resource
    private WineHistoryPriceMapper wineHistoryPriceMapper;

    /**
     * 新增实时价格信息
     * @param record
     */
    public void addWineHistoryPrice(WineHistoryPrice record) {
        wineHistoryPriceMapper.insertSelective(record);
    }

    /**
     * 根据实时价格主键 id 查询产品的实时价格信息
     * @param id
     * @return
     */
    public WineHistoryPrice queryByPrimaryKey(String id) {
        return wineHistoryPriceMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据实时价格的产品 id 查询实时价格信息
     * @param wineRealtimePriceId
     * @return
     */
    public WineHistoryPrice queryByWineRealtimePriceId(String wineRealtimePriceId) {
        return wineHistoryPriceMapper.selectByWineRealtimePriceId(wineRealtimePriceId);
    }

    /**
     * 根据实时价格的主键 id 删除实时价格信息
     * @param id
     * @return
     */
    public void deleteByPrimaryKey(String id){
        wineHistoryPriceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据复合条件查询历史价格记录
     * @param queryExample
     * @return
     */
    public List<WineHistoryPrice> queryByExample(WineHistoryPriceQueryDto queryExample){
        return wineHistoryPriceMapper.selectByExample(queryExample);
    }

}
