package com.damai.wine.service.price;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.service.request.price.WineRealtimePriceAddRequest;
import com.damai.wine.api.service.request.price.WineRealtimePriceUpdateRequest;
import com.damai.wine.api.service.response.price.WineRealtimePriceDto;
import com.damai.wine.common.utils.DateUtils;
import com.damai.wine.dao.model.WineHistoryPrice;
import com.damai.wine.dao.model.WineRealtimePrice;
import com.damai.wine.dao.repository.WineHistoryPriceRepository;
import com.damai.wine.dao.repository.WineRealtimePriceRepository;
import com.damai.wine.rpcservice.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * 事务层服务
 */
@Slf4j
@Service("wineRealtimePriceTransactionService")
public class WineRealtimePriceTransactionService extends BaseService {

    @Autowired
    private WineRealtimePriceRepository wineRealtimePriceRepository;

    @Autowired
    private WineHistoryPriceRepository wineHistoryPriceRepository;

    /**
     * 新增酒的价格信息
     * @return
     */
    @Transactional
    public String insertWinePrice(WineRealtimePriceAddRequest wineRealtimePriceAddRequest){
        if (log.isInfoEnabled()) {
            log.info("[insertWinePrice] 请求参数:request{}", JSONObject.toJSONString(wineRealtimePriceAddRequest));
        }
        validateCreateWinePrice(wineRealtimePriceAddRequest);

        // 生成主键id
        if (StringUtils.isBlank(wineRealtimePriceAddRequest.getId())){
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            wineRealtimePriceAddRequest.setId(id);
        }

        WineHistoryPrice wineHistoryPrice = buildWineHistoryPrice(wineRealtimePriceAddRequest);
        wineHistoryPrice.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        wineRealtimePriceRepository.addWineRealtimePrice(copyProperties(wineRealtimePriceAddRequest, WineRealtimePrice.class));
        wineHistoryPriceRepository.addWineHistoryPrice(wineHistoryPrice);

        return wineRealtimePriceAddRequest.getId();
    }

    /**
     * 新增酒的价格参数校验
     * @param request
     */
    private void validateCreateWinePrice(WineRealtimePriceAddRequest request){
        Assert.notNull(request, "WineRealtimePriceAddRequest is null");
        Assert.notNull(request.getWineProductId(), "wineProductId is null");
        Assert.notNull(request.getPrice(), "price is null");
        Assert.notNull(request.getOperator(), "operator is null");
        Assert.notNull(request.getChannelResource(), "channelResource is null");
    }

    /**
     * 根据实时价格该请求构建历史价格记录请求
     * @param request
     * @return
     */
    private WineHistoryPrice buildWineHistoryPrice(WineRealtimePriceAddRequest request){
        WineHistoryPrice wineHistoryPrice = new WineHistoryPrice();
        wineHistoryPrice.setWineRealtimePriceId(request.getId());
        wineHistoryPrice.setWineProductId(request.getWineProductId());
        wineHistoryPrice.setPrice(request.getPrice());
        wineHistoryPrice.setCreateDay(DateUtils.formatString(new Date(), "yyyy-MM-dd"));
        wineHistoryPrice.setChannelResource(request.getChannelResource());
        return wineHistoryPrice;
    }

    /**
     * 更新酒的价格信息
     * 1.新增 新的实时价格记录
     * 2.删除 老的实时价格记录
     * @return
     */
    @Transactional
    public WineRealtimePriceDto updateWinePrice(WineRealtimePriceUpdateRequest wineRealtimePriceUpdateRequest){
        if (log.isInfoEnabled()) {
            log.info("[updateWinePrice] 请求参数:request{}", JSONObject.toJSONString(wineRealtimePriceUpdateRequest));
        }

        validateUpdateWinePrice(wineRealtimePriceUpdateRequest);

        // 构建实时价格记录的新增对象，重新生成 id
        WineRealtimePrice addWineRealtimePrice = copyProperties(wineRealtimePriceUpdateRequest, WineRealtimePrice.class);
        addWineRealtimePrice.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        wineRealtimePriceRepository.addWineRealtimePrice(addWineRealtimePrice);
        wineRealtimePriceRepository.deleteByPrimaryKey(wineRealtimePriceUpdateRequest.getId());

        return copyProperties(addWineRealtimePrice, WineRealtimePriceDto.class);
    }


    /**
     * 修改酒的价格参数校验
     * @param request
     */
    private void validateUpdateWinePrice(WineRealtimePriceUpdateRequest request){
        Assert.notNull(request, "WineRealtimePriceUpdateRequest is null");
        Assert.notNull(request.getId(), "id is null");
        Assert.notNull(request.getPrice(), "price is null");
        Assert.notNull(request.getOperator(), "operator is null");
        Assert.notNull(request.getChannelResource(), "channelResource is null");
    }

}
