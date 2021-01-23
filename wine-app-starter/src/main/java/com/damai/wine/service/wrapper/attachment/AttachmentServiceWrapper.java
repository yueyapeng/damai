package com.damai.wine.service.wrapper.attachment;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.service.request.attachment.AttachmentAddRequest;
import com.damai.wine.api.service.response.attachment.AttachmentDto;
import com.damai.wine.service.BaseService;
import com.damai.wine.service.attachment.AttachmentService;
import com.damai.wine.web.controller.attachment.request.UploadAttachmentRequest;
import com.damai.wine.web.controller.attachment.response.UploadAttachmentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AttachmentServiceWrapper extends BaseService {

    @Autowired
    AttachmentService attachmentService;

    /**
     * 新增附件信息
     * @param request
     * @return
     */
    public String insertAttachment(UploadAttachmentRequest request){
        if (log.isInfoEnabled()) {
            log.info("请求参数:{}", JSONObject.toJSONString(request));
        }
        // 1.新增附件信息
        AttachmentAddRequest attachmentAddRequest = new AttachmentAddRequest();
        BeanUtils.copyProperties(request, attachmentAddRequest);
        return attachmentService.insertAttachment(attachmentAddRequest);
    }

    /**
     * 根据借款单主键 id 查询当前借款单关联的附件信息
     * @param orderId
     * @return
     */
    public List<UploadAttachmentResponse> queryAttachmentResponseByOrderId(String orderId, String attachmentType){
        if (log.isInfoEnabled()) {
            log.info("请求参数:orderId:{}, attachmentType:{}", orderId, attachmentType);
        }
        List<AttachmentDto> attachmentDtoList = attachmentService.queryAttachmentByRealtionIdAndType(orderId, attachmentType);
        if (attachmentDtoList != null){
            return listTransition(attachmentDtoList, UploadAttachmentResponse.class);
        }
        return null;
    }


}
