package com.damai.wine.rpcservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.Result;
import com.damai.wine.api.common.WrapResult;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.service.AttachmentRpcService;
import com.damai.wine.api.service.request.attachment.AttachmentAddRequest;
import com.damai.wine.api.service.request.attachment.AttachmentQueryRequest;
import com.damai.wine.api.service.response.attachment.AttachmentDto;
import com.damai.wine.service.attachment.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 提供对外的dubbo服务
 */
@Slf4j
@Service
public class AttachmentRpcServiceImpl extends BaseService implements AttachmentRpcService {


    @Autowired
    AttachmentService attachmentService;

    @Override
    public Result<AttachmentDto> addAttachment(AttachmentAddRequest request) {
        if (log.isInfoEnabled()){
            log.info("[addAttachment] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<AttachmentDto> result = WrapResult.success();
        try {
            validateCreateAttachment(request);
            String id = attachmentService.insertAttachment(request);
            request.setId(id);
            result.setData(copyProperties(request, AttachmentDto.class));
        }catch (IllegalArgumentException e){
            log.error("[addAttachment] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[addAttachment] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<AttachmentDto> queryAttachmentById(String id) {
        if (log.isInfoEnabled()){
            log.info("[queryAttachmentById] 请求参数 id:{}", id);
        }
        Result<AttachmentDto> result = WrapResult.success();
        try {
            Assert.notNull(id, "id is null");
            AttachmentDto attachmentDto = attachmentService.queryAttachmentById(id);
            if (attachmentDto != null){
                result.setData(attachmentDto);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryAttachmentById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryAttachmentById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<List<AttachmentDto>> queryAttachmentByRealtionIdAndType(String realtionId, String attachmentType) {
        if (log.isInfoEnabled()) {
            log.info("[queryAttachmentByRealtionIdAndType] 请求参数:realtionId:{}, attachmentType:{}", realtionId, attachmentType);
        }
        Result<List<AttachmentDto>> result = WrapResult.success();
        try {
            Assert.notNull(realtionId, "realtionId is null");
            Assert.notNull(attachmentType, "attachmentType is null");
            List<AttachmentDto> attachmentDtoList = attachmentService.queryAttachmentByRealtionIdAndType(realtionId, attachmentType);
            if (attachmentDtoList != null){
                result.setData(attachmentDtoList);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryAttachmentByRealtionIdAndType] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryAttachmentByRealtionIdAndType] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<List<AttachmentDto>> queryAttachmentByExample(AttachmentQueryRequest request) {
        if (log.isInfoEnabled()){
            log.info("[queryAttachmentByExample] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<List<AttachmentDto>> result = WrapResult.success();
        try {
            List<AttachmentDto> lists = attachmentService.queryAttachmentByExample(request);
            if (lists != null){
                result.setData(lists);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryAttachmentByExample] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryAttachmentByExample] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    private void validateCreateAttachment(AttachmentAddRequest request){
        Assert.notNull(request, "AttachmentAddRequest is null");
        Assert.notNull(request.getRelationId(), "relationId is null");
        Assert.notNull(request.getAttachmentType(), "attachmentType is null");
        Assert.notNull(request.getAttachmentName(), "attachmentName is null");
        Assert.notNull(request.getAttachmentDetailType(), "attachmentDetailType is null");
        Assert.notNull(request.getAttachmentUrl(), "attachmentUrl is null");
        Assert.notNull(request.getDataSource(), "dataSource is null");
    }

}
