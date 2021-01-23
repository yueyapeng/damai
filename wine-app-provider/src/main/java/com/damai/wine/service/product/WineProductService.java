package com.damai.wine.service.product;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.ProductStatusEnum;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.api.service.request.product.WineProductAddRequest;
import com.damai.wine.api.service.request.product.WineProductQueryRequest;
import com.damai.wine.api.service.request.product.WineProductUpdateRequest;
import com.damai.wine.api.service.response.product.WineProductDto;
import com.damai.wine.common.exception.BizException;
import com.damai.wine.dao.model.WineProduct;
import com.damai.wine.dao.repository.WineProductRepository;
import com.damai.wine.dto.request.product.WineProductQueryDto;
import com.damai.wine.rpcservice.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class WineProductService extends BaseService {

    @Autowired
    private WineProductRepository wineProductRepository;

    /**
     * 新增酒类产品信息
     * @return
     */
    public String insertWineProduct(WineProductAddRequest wineProductAddRequest){
        if (log.isInfoEnabled()) {
            log.info("请求参数:{}", JSONObject.toJSONString(wineProductAddRequest));
        }
        try {
            validateCreateWineProduct(wineProductAddRequest);

            // 生成主键id
            if (StringUtils.isBlank(wineProductAddRequest.getId())){
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                wineProductAddRequest.setId(id);
            }

            WineProduct wineProduct = new WineProduct();
            BeanUtils.copyProperties(wineProductAddRequest, wineProduct);
            wineProductRepository.addWineProduct(wineProduct);
        } catch (IllegalArgumentException e){
            log.error("[insertWineProduct] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        } catch (Exception e){
            log.error("[insertWineProduct] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
        return wineProductAddRequest.getId();
    }

    private void validateCreateWineProduct(WineProductAddRequest request){
        Assert.notNull(request, "WineProductAddRequest is null");
        Assert.notNull(request.getWineType(), "wineType is null");
        Assert.notNull(request.getWineBrand(), "wineBrand is null");
        Assert.notNull(request.getDegree(), "degree is null");
        Assert.notNull(request.getCapacity(), "capacity is null");
        if (request.getWineBrand().equals(WineBrandEnum.SHENG_XIAO)){
            Assert.notNull(request.getChineseZodiac(), "chineseZodiac is null");
        }else {
            Assert.notNull(request.getProductionYear(), "productionYear is null");
        }
        // 包装(1:散装、2:原箱)
        Assert.notNull(request.getPackaging(), "packaging is null");
        // 如果packaging=1 散装的瓶数、 如果packaging=2 原箱包装，每箱的瓶数
        Assert.notNull(request.getBottleNumber(), "bottleNumber is null");

        // 产品默认是未上架的
        request.setStatus(ProductStatusEnum.N.getValue());
    }

    /**
     * 根据产品主键 id 查询产品信息
     * @param id
     * @return
     */
    public WineProductDto queryWineProductById(String id){
        if (log.isInfoEnabled()) {
            log.info("请求参数:id:{}", id);
        }
        try {
            Assert.notNull(id, "id is null");
            WineProduct wineProduct = wineProductRepository.queryByPrimaryKey(id);
            if (wineProduct != null){
               return copyProperties(wineProduct, WineProductDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryWineProductById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryWineProductById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     * 根据复合条件查询产品信息
     * @param wineProductQueryRequest
     * @return
     */
    public List<WineProductDto> queryWineProductByExample(WineProductQueryRequest wineProductQueryRequest){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(wineProductQueryRequest));
        }
        try {
//            validateQueryWineProduct(wineProductQueryRequest);

            WineProductQueryDto wineProductQueryDto = new WineProductQueryDto();
            BeanUtils.copyProperties(wineProductQueryRequest, wineProductQueryDto);
            List<WineProduct> wineProductList = wineProductRepository.queryByExample(wineProductQueryDto);
            if (wineProductList != null){
                return listTransition(wineProductList, WineProductDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryWineProductByExample] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryWineProductByExample] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     *
     * @param request
     */
//    private void validateQueryWineProduct(WineProductQueryRequest request){
//        Assert.notNull(request, "WineProductAddRequest is null");
//        Assert.notNull(request.getWineType(), "wineType is null");
//        Assert.notNull(request.getWineBrand(), "wineBrand is null");
//        Assert.notNull(request.getProductionYear(), "productionYear is null");
//        Assert.notNull(request.getDegree(), "degree is null");
//        Assert.notNull(request.getCapacity(), "capacity is null");
//        if (request.getWineBrand().equals(WineBrandEnum.SHENG_XIAO)){
//            Assert.notNull(request.getChineseZodiac(), "chineseZodiac is null");
//        }
//        // 包装(1:散装、2:原箱)
//        Assert.notNull(request.getPackaging(), "packaging is null");
//        // 如果packaging=1 散装的瓶数、 如果packaging=2 原箱包装，每箱的瓶数
//        Assert.notNull(request.getBottleNumber(), "bottleNumber is null");
//    }

    /**
     * 根据产品的主键 id 更新产品信息
     * @param request
     */
    public void updateWineProductById(WineProductUpdateRequest request){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(request));
        }
        try {
            Assert.notNull(request, "WineProductUpdateRequest is null");
            Assert.notNull(request.getId(), "id is null");
            WineProduct wineProduct = new WineProduct();
            BeanUtils.copyProperties(request, wineProduct);
            wineProductRepository.updateByPrimaryKeySelective(wineProduct);
        }catch (IllegalArgumentException e){
            log.error("[updateWineProductById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[updateWineProductById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


}
