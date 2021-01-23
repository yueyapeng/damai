package com.damai.wine.service.price;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.service.request.price.WineRealtimePriceQueryRequest;
import com.damai.wine.api.service.response.price.WineRealtimePriceDto;
import com.damai.wine.common.exception.BizException;
import com.damai.wine.dao.model.WineRealtimePrice;
import com.damai.wine.dao.repository.WineRealtimePriceRepository;
import com.damai.wine.dto.request.price.WineRealtimePriceQueryDto;
import com.damai.wine.rpcservice.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
public class WineRealtimePriceService extends BaseService {

    @Autowired
    private WineRealtimePriceRepository wineRealtimePriceRepository;

    /**
     * 根据实时价格记录的主键 id 查询价格信息
     * @param id
     * @return
     */
    public WineRealtimePriceDto queryWineRealtimePriceById(String id){
        if (log.isInfoEnabled()) {
            log.info("请求参数:id:{}", id);
        }
        WineRealtimePriceDto wineRealtimePriceDto = new WineRealtimePriceDto();
        try {
            Assert.notNull(id, "id is null");
            WineRealtimePrice wineRealtimePrice = wineRealtimePriceRepository.queryByPrimaryKey(id);
            if (wineRealtimePrice != null){
               return copyProperties(wineRealtimePrice, WineRealtimePriceDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryWineRealtimePriceById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryWineRealtimePriceById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     * 根据实时价格记录 wineProductId 查询价格信息
     * @param wineProductId
     * @return
     */
    public WineRealtimePriceDto queryWineRealtimePriceByWineProductId(String wineProductId){
        if (log.isInfoEnabled()) {
            log.info("请求参数:wineProductId:{}", wineProductId);
        }
        WineRealtimePriceDto wineRealtimePriceDto = new WineRealtimePriceDto();
        try {
            Assert.notNull(wineProductId, "wineProductId is null");
            WineRealtimePrice wineRealtimePrice = wineRealtimePriceRepository.queryByWineProductId(wineProductId);
            if (wineRealtimePrice != null){
               return copyProperties(wineRealtimePrice, WineRealtimePriceDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryWineRealtimePriceByWineProductId] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryWineRealtimePriceByWineProductId] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


    /**
     * 根据复合条件查询实时价格记录
     * @param request
     * @return
     */
    public List<WineRealtimePriceDto> queryWineRealtimePriceByExample(WineRealtimePriceQueryRequest request){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(request));
        }
        try {
            WineRealtimePriceQueryDto wineRealtimePriceQueryDto = new WineRealtimePriceQueryDto();
            BeanUtils.copyProperties(request, wineRealtimePriceQueryDto);
            List<WineRealtimePrice> wineRealtimePriceList = wineRealtimePriceRepository.queryByExample(wineRealtimePriceQueryDto);
            if (wineRealtimePriceList != null){
                return listTransition(wineRealtimePriceList, WineRealtimePriceDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryWineRealtimePriceByExample] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryWineRealtimePriceByExample] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


    /**
     * 根据产品的主键 id 更新产品信息
     * @param id
     */
    public void deleteWineRealtimePriceById(String id){
        if (log.isInfoEnabled()) {
            log.info("请求参数:id:{}", id);
        }
        try {
            Assert.notNull(id, "id is null");
            wineRealtimePriceRepository.deleteByPrimaryKey(id);
        }catch (IllegalArgumentException e){
            log.error("[updateWineProductById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[updateWineProductById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


}
