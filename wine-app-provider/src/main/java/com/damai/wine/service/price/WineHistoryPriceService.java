package com.damai.wine.service.price;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.service.request.price.WineHistoryPriceQueryRequest;
import com.damai.wine.api.service.response.price.WineHistoryPriceDto;
import com.damai.wine.common.exception.BizException;
import com.damai.wine.dao.model.WineHistoryPrice;
import com.damai.wine.dao.repository.WineHistoryPriceRepository;
import com.damai.wine.dto.request.price.WineHistoryPriceQueryDto;
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
public class WineHistoryPriceService extends BaseService {

    @Autowired
    private WineHistoryPriceRepository wineHistoryPriceRepository;

    /**
     * 根据实时价格记录的主键 id 查询价格信息
     * @param id
     * @return
     */
    public WineHistoryPriceDto queryWineHistoryPriceById(String id){
        if (log.isInfoEnabled()) {
            log.info("请求参数:id:{}", id);
        }
        try {
            Assert.notNull(id, "id is null");
            WineHistoryPrice wineHistoryPrice = wineHistoryPriceRepository.queryByPrimaryKey(id);
            if (wineHistoryPrice != null){
                return copyProperties(wineHistoryPrice, WineHistoryPriceDto.class);
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
     * 根据实时价格记录的主键 id 查询价格信息
     * @param wineRealtimePriceId
     * @return
     */
    public WineHistoryPriceDto queryWineHistoryPriceByWineRealtimePriceId(String wineRealtimePriceId){
        if (log.isInfoEnabled()) {
            log.info("请求参数:wineRealtimePriceId:{}", wineRealtimePriceId);
        }
        try {
            Assert.notNull(wineRealtimePriceId, "wineRealtimePriceId is null");
            WineHistoryPrice wineHistoryPrice = wineHistoryPriceRepository.queryByWineRealtimePriceId(wineRealtimePriceId);
            if (wineHistoryPrice != null){
                return copyProperties(wineHistoryPrice, WineHistoryPriceDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryWineHistoryPriceByWineRealtimePriceId] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryWineHistoryPriceByWineRealtimePriceId] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


    /**
     * 根据复合条件查询实时价格记录
     * @param request
     * @return
     */
    public List<WineHistoryPriceDto> queryWineHistoryPriceByExample(WineHistoryPriceQueryRequest request){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(request));
        }
        try {
            WineHistoryPriceQueryDto wineHistoryPriceQueryDto = new WineHistoryPriceQueryDto();
            BeanUtils.copyProperties(request, wineHistoryPriceQueryDto);
            List<WineHistoryPrice> wineHistoryPriceList = wineHistoryPriceRepository.queryByExample(wineHistoryPriceQueryDto);
            if (wineHistoryPriceList != null){
                return listTransition(wineHistoryPriceList, WineHistoryPriceDto.class);
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

}
