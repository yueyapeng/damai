package com.damai.wine.dao.repository;

import com.damai.wine.dao.mapper.WineOrderMapper;
import com.damai.wine.dao.model.WineOrder;
import com.damai.wine.dto.request.order.WineOrderQueryDto;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class WineOrderRepository {

    @Resource
    private WineOrderMapper wineOrderMapper;

    /**
     * 新增订单信息
     * @param record
     */
    public void addWineOrder(WineOrder record) {
        wineOrderMapper.insertSelective(record);
    }

    /**
     * 根据订单的主键 id 查询订单信息
     * @param id
     * @return
     */
    public WineOrder queryByPrimaryKey(String id) {
        return wineOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据订单复合条件查询订单信息
     * @param record
     * @return
     */
    public List<WineOrder> queryByExample(WineOrderQueryDto record) {
        return wineOrderMapper.selectByExample(record);
    }

    /**
     * 根据订单主键更新订单信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(WineOrder record){
       return  wineOrderMapper.updateByPrimaryKeySelective(record);
    }

}
