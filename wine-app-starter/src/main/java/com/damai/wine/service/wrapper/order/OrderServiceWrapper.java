package com.damai.wine.service.wrapper.order;

import com.damai.wine.api.common.enums.OrderStatusEnum;
import com.damai.wine.api.service.request.order.WineOrderAddRequest;
import com.damai.wine.api.service.request.order.WineOrderQueryRequest;
import com.damai.wine.api.service.request.order.WineOrderUpdateRequest;
import com.damai.wine.api.service.response.attachment.AttachmentDto;
import com.damai.wine.api.service.response.order.WineOrderDto;
import com.damai.wine.common.enums.AttachmentTypeEnum;
import com.damai.wine.service.BaseService;
import com.damai.wine.service.attachment.AttachmentService;
import com.damai.wine.service.order.WineOrderService;
import com.damai.wine.web.controller.order.request.OrderQueryRequest;
import com.damai.wine.web.controller.order.request.PostGoodsRequest;
import com.damai.wine.web.controller.order.request.SubmitOrderRequest;
import com.damai.wine.web.controller.order.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceWrapper extends BaseService {

    @Autowired
    WineOrderService wineOrderService;

    @Autowired
    AttachmentService attachmentService;

    /**
     * 下单操作
     * @param request
     * @return
     */
    public String submitOrder(SubmitOrderRequest request){
        // 1.订单创建
        WineOrderAddRequest wineOrderAddRequest = new WineOrderAddRequest();
        BeanUtils.copyProperties(request, wineOrderAddRequest);
        // 初始化状态
        wineOrderAddRequest.setStatus(OrderStatusEnum.INIT.getValue());
        return wineOrderService.insertWineOrder(wineOrderAddRequest);
    }

    /**
     * 订单列表查询
     * @param queryRequest
     * @return
     */
    public List<OrderResponse> queryWineOrders(OrderQueryRequest queryRequest){
        WineOrderQueryRequest request = new WineOrderQueryRequest();
        BeanUtils.copyProperties(queryRequest, request);
        List<WineOrderDto> resultList = wineOrderService.queryWineOrderByExample(request);
        if (resultList != null){
            return listTransition(resultList, OrderResponse.class);
        }
        return null;
    }

    /**
     * 查询订单详情
     * @param id
     * @param attachmentFlag 是否需要附件
     * @return
     */
    public OrderResponse queryWineOrderDetail(String id, boolean attachmentFlag){
        OrderResponse result = new OrderResponse();
        // 1.查询订单
        WineOrderDto wineOrderDto = wineOrderService.queryWineOrderById(id);

        // 2.根据订单id，查询关联的 图片信息（邮寄图片和退回图片等）
        if (wineOrderDto != null){
            BeanUtils.copyProperties(wineOrderDto, result);
            if (attachmentFlag){
                List<AttachmentDto> attachmentDtoList = attachmentService.queryAttachmentByRealtionIdAndType(wineOrderDto.getId(), AttachmentTypeEnum.GOODS.getValue());
                result.setGoodsPicture(attachmentDtoList);
            }
        }
        return result;
    }

    /**
     * 邮寄商品
     * @param request
     * @return
     */
    public OrderResponse postGoods(PostGoodsRequest request){
        OrderResponse result = new OrderResponse();
        // 1.查询订单
        WineOrderUpdateRequest updateRequest = new WineOrderUpdateRequest();
        BeanUtils.copyProperties(request, updateRequest);
        updateRequest.setStatus(OrderStatusEnum.POSTING.getValue());
        updateRequest.setMailTime(new Date());
        wineOrderService.updateWineOrderById(updateRequest);
        return result;
    }

}
