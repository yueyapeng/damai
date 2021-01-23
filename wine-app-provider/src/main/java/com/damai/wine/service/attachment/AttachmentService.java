package com.damai.wine.service.attachment;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.api.service.request.attachment.AttachmentAddRequest;
import com.damai.wine.api.service.request.attachment.AttachmentQueryRequest;
import com.damai.wine.api.service.request.order.WineOrderAddRequest;
import com.damai.wine.api.service.request.order.WineOrderQueryRequest;
import com.damai.wine.api.service.request.order.WineOrderUpdateRequest;
import com.damai.wine.api.service.response.attachment.AttachmentDto;
import com.damai.wine.api.service.response.order.WineOrderDto;
import com.damai.wine.common.exception.BizException;
import com.damai.wine.dao.model.Attachment;
import com.damai.wine.dao.model.WineOrder;
import com.damai.wine.dao.repository.AttachmentRepository;
import com.damai.wine.dao.repository.WineOrderRepository;
import com.damai.wine.dto.request.attachment.AttachmentQueryDto;
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
public class AttachmentService extends BaseService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    /**
     * 新增附件信息
     * @return
     */
    public String insertAttachment(AttachmentAddRequest attachmentAddRequest){
        if (log.isInfoEnabled()) {
            log.info("请求参数:{}", JSONObject.toJSONString(attachmentAddRequest));
        }
        try {
            validateCreateAttachment(attachmentAddRequest);

            // 生成主键id
            if (StringUtils.isBlank(attachmentAddRequest.getId())){
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                attachmentAddRequest.setId(id);
            }

            Attachment attachment = new Attachment();
            BeanUtils.copyProperties(attachmentAddRequest, attachment);
            attachmentRepository.addAttachment(attachment);
        } catch (IllegalArgumentException e){
            log.error("[insertAttachment] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        } catch (Exception e){
            log.error("[insertAttachment] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
        return attachmentAddRequest.getId();
    }

    /**
     * 创建订单校验参数
     * @param request
     */
    private void validateCreateAttachment(AttachmentAddRequest request){
        Assert.notNull(request, "AttachmentAddRequest is null");
        Assert.notNull(request.getRelationId(), "relationId is null");
        Assert.notNull(request.getAttachmentType(), "attachmentType is null");
        Assert.notNull(request.getAttachmentName(), "attachmentName is null");
        Assert.notNull(request.getAttachmentDetailType(), "attachmentDetailType is null");
        Assert.notNull(request.getAttachmentUrl(), "attachmentUrl is null");
        Assert.notNull(request.getDataSource(), "dataSource is null");
    }

    /**
     * 根据附件主键 id 查询附件信息
     * @param id
     * @return
     */
    public AttachmentDto queryAttachmentById(String id){
        if (log.isInfoEnabled()) {
            log.info("请求参数:id:{}", id);
        }
        try {
            Assert.notNull(id, "id is null");
            Attachment attachment = attachmentRepository.queryByPrimaryKey(id);
            if (attachment != null){
                return copyProperties(attachment, AttachmentDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryAttachmentById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryAttachmentById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     * 根据附件主键 id 查询附件信息
     * @param realtionId
     * @param attachmentType
     * @return
     */
    public List<AttachmentDto> queryAttachmentByRealtionIdAndType(String realtionId, String attachmentType){
        if (log.isInfoEnabled()) {
            log.info("请求参数:realtionId:{}, attachmentType:{}", realtionId, attachmentType);
        }
        try {
            Assert.notNull(realtionId, "realtionId is null");
            Assert.notNull(attachmentType, "attachmentType is null");
            List<Attachment> attachmentList = attachmentRepository.queryByRealtionIdAndType(realtionId, attachmentType);
            if (attachmentList != null){
                return listTransition(attachmentList, AttachmentDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryAttachmentByRealtionIdAndType] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryAttachmentByRealtionIdAndType] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     * 根据复合条件查询附件信息
     * @param attachmentQueryRequest
     * @return
     */
    public List<AttachmentDto> queryAttachmentByExample(AttachmentQueryRequest attachmentQueryRequest){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(attachmentQueryRequest));
        }
        try {
            AttachmentQueryDto attachmentQueryDto = new AttachmentQueryDto();
            BeanUtils.copyProperties(attachmentQueryRequest, attachmentQueryDto);
            List<Attachment> attachmentList = attachmentRepository.queryByExample(attachmentQueryDto);
            if (attachmentList != null){
                return listTransition(attachmentList, AttachmentDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryAttachmentByExample] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryAttachmentByExample] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

}
