package com.damai.wine.dao.repository;

import com.damai.wine.dao.mapper.WineRealtimePriceMapper;
import com.damai.wine.dao.model.WineRealtimePrice;
import com.damai.wine.dto.request.price.WineRealtimePriceQueryDto;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class WineRealtimePriceRepository {

    @Resource
    private WineRealtimePriceMapper wineRealtimePriceMapper;

    /**
     * 新增实时价格信息
     * @param record
     */
    public void addWineRealtimePrice(WineRealtimePrice record) {
        wineRealtimePriceMapper.insertSelective(record);
    }

    /**
     * 根据实时价格主键 id 查询产品的实时价格信息
     * @param id
     * @return
     */
    public WineRealtimePrice queryByPrimaryKey(String id) {
        return wineRealtimePriceMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据实时价格的产品 id 查询实时价格信息
     * @param id
     * @return
     */
    public WineRealtimePrice queryByWineProductId(String id) {
        return wineRealtimePriceMapper.selectByWineProductId(id);
    }

    /**
     * 根据实时价格复合条件查询实时价格信息
     * @param record
     * @return
     */
    public List<WineRealtimePrice> queryByExample(WineRealtimePriceQueryDto record) {
        return wineRealtimePriceMapper.selectByExample(record);
    }

    /**
     * 根据实时价格的主键 id 删除实时价格信息
     * @param id
     * @return
     */
    public void deleteByPrimaryKey(String id){
        wineRealtimePriceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据实时价格的主键 id 删除实时价格信息
     * @param wineProductId
     * @return
     */
    public void deleteByWineProductId(String wineProductId){
        wineRealtimePriceMapper.deleteByWineProductId(wineProductId);
    }

}
