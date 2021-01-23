package com.damai.wine.service.order;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.api.service.request.order.WineOrderAddRequest;
import com.damai.wine.api.service.request.order.WineOrderQueryRequest;
import com.damai.wine.api.service.request.order.WineOrderUpdateRequest;
import com.damai.wine.api.service.response.order.WineOrderDto;
import com.damai.wine.common.exception.BizException;
import com.damai.wine.dao.model.WineOrder;
import com.damai.wine.dao.repository.WineOrderRepository;
import com.damai.wine.dto.request.order.WineOrderQueryDto;
import com.damai.wine.rpcservice.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class WineOrderService extends BaseService {

    @Autowired
    private WineOrderRepository wineOrderRepository;

    /**
     * 新增订单信息
     * @return
     */
    public String insertWineOrder(WineOrderAddRequest wineOrderAddRequest){
        if (log.isInfoEnabled()) {
            log.info("请求参数:{}", JSONObject.toJSONString(wineOrderAddRequest));
        }
        try {
            validateCreateWineOrder(wineOrderAddRequest);

            // 生成主键id
            if (StringUtils.isBlank(wineOrderAddRequest.getId())){
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                wineOrderAddRequest.setId(id);
            }

            WineOrder wineOrder = new WineOrder();
            BeanUtils.copyProperties(wineOrderAddRequest, wineOrder);
            wineOrderRepository.addWineOrder(wineOrder);
        } catch (IllegalArgumentException e){
            log.error("[insertWineOrder] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        } catch (Exception e){
            log.error("[insertWineOrder] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
        return wineOrderAddRequest.getId();
    }

    /**
     * 创建订单校验参数
     * @param request
     */
    private void validateCreateWineOrder(WineOrderAddRequest request){
        Assert.notNull(request, "WineOrderAddRequest is null");
        Assert.notNull(request.getWineProductId(), "wineProductId is null");
        Assert.notNull(request.getUserId(), "userId is null");
        Assert.notNull(request.getWineRealtimePriceId(), "wineRealtimePriceId is null");
        Assert.notNull(request.getWineType(), "wineType is null");
        Assert.notNull(request.getWineBrand(), "wineBrand is null");
        if (request.getWineBrand().equals(WineBrandEnum.SHENG_XIAO)){
            Assert.notNull(request.getChineseZodiac(), "chineseZodiac is null");
        }else {
            Assert.notNull(request.getProductionYear(), "productionYear is null");
        }
        Assert.notNull(request.getPrice(), "price is null");
        Assert.notNull(request.getPrice(), "price is null");
        Assert.notNull(request.getBottleNumber(), "bottleNumber is null");
        Assert.notNull(request.getTotalAmount(), "totalAmount is null");
        BigDecimal calculateTotalAomount = new BigDecimal(request.getPrice()).multiply(new BigDecimal(request.getBottleNumber()));
        if (calculateTotalAomount.compareTo(new BigDecimal(request.getTotalAmount())) != 0){
            log.error("计算金额不一致。price:{},bottleNumber:{},totalAmount:{}", request.getPrice(), request.getBottleNumber(), request.getTotalAmount());
            throw new IllegalArgumentException("商品单价乘以数量与总金额不一致");
        }
        Assert.notNull(request.getColType(), "colType is null");
        Assert.notNull(request.getColAccount(), "colAccount is null");
        Assert.notNull(request.getColName(), "colName is null");
        Assert.notNull(request.getStatus(), "status is null");
    }

    /**
     * 根据产品主键 id 查询产品信息
     * @param id
     * @return
     */
    public WineOrderDto queryWineOrderById(String id){
        if (log.isInfoEnabled()) {
            log.info("请求参数:id:{}", id);
        }
        try {
            Assert.notNull(id, "id is null");
            WineOrder wineOrder = wineOrderRepository.queryByPrimaryKey(id);
            if (wineOrder != null){
                return copyProperties(wineOrder, WineOrderDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryWineOrderById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryWineOrderById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     * 根据复合条件查询订单信息
     * @param wineOrderQueryRequest
     * @return
     */
    public List<WineOrderDto> queryWineOrderByExample(WineOrderQueryRequest wineOrderQueryRequest){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(wineOrderQueryRequest));
        }
        try {
            WineOrderQueryDto wineOrderQueryDto = new WineOrderQueryDto();
            BeanUtils.copyProperties(wineOrderQueryRequest, wineOrderQueryDto);
            List<WineOrder> wineOrderList = wineOrderRepository.queryByExample(wineOrderQueryDto);
            if (wineOrderList != null){
                return listTransition(wineOrderList, WineOrderDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryWineOrderByExample] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryWineOrderByExample] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


    /**
     * 根据订单的主键 id 更新订单信息
     * @param request
     */
    public void updateWineOrderById(WineOrderUpdateRequest request){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(request));
        }
        try {
            Assert.notNull(request, "WineOrderUpdateRequest is null");
            Assert.notNull(request.getId(), "id is null");
            WineOrder wineOrder = new WineOrder();
            BeanUtils.copyProperties(request, wineOrder);
            wineOrderRepository.updateByPrimaryKey(wineOrder);
        }catch (IllegalArgumentException e){
            log.error("[updateWineOrderById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[updateWineOrderById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


}
