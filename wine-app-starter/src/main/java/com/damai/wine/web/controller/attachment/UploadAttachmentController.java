package com.damai.wine.web.controller.attachment;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.AttachmentDataSourceEnum;
import com.damai.wine.common.AppResult;
import com.damai.wine.common.AppWrapResult;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.common.enums.AttachmentDetailTypeEnum;
import com.damai.wine.common.enums.AttachmentTypeEnum;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.third.oss.AliyunOssService;
import com.damai.wine.service.third.oss.request.OSSUploadRequest;
import com.damai.wine.service.wrapper.attachment.AttachmentServiceWrapper;
import com.damai.wine.web.controller.BaseController;
import com.damai.wine.web.controller.attachment.request.UploadAttachmentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/attachment")
public class UploadAttachmentController extends BaseController {

    @Autowired
    AliyunOssService aliyunOssService;

    @Autowired
    AttachmentServiceWrapper attachmentServiceWrapper;

    @ResponseBody
    @RequestMapping("/upload")
    public AppResult<String> upload(UploadAttachmentRequest request) {
        String traceId = getTraceId();
        if (log.isInfoEnabled()){
            log.info("[upload]traceId:{},请求参数:request{}", traceId, JSONObject.toJSONString(request));
        }
        AppResult result = AppWrapResult.success();
        try {
            // 1.参数校验
            validateUploadParam(request);

            // 2.上传到 oss
            OSSUploadRequest uploadRequest = buildOSSUploadRequest(request);
            String url = aliyunOssService.upload(uploadRequest);

            // 3.上传 oss 成功后，本地保存订单和图片得关联关系
            UploadAttachmentRequest uploadAttachmentRequest = buildUploadAttachmentRequest(request, url);
            attachmentServiceWrapper.insertAttachment(uploadAttachmentRequest);

            result.setData(url);
            return result;
        }  catch (WineException error) {
            log.error("[upload]traceId:{},上传附件失败,{}", traceId, JSONObject.toJSONString(error));
            result.setRetCode(error.getRetCode());
            result.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[upload]traceId:{},上传附件异常", traceId, e);
            result.setRetCode(ResponseResultCode.FAILED.getCode());
            result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }

    private void validateUploadParam(UploadAttachmentRequest request){
        try {
            Assert.notNull(request, "UploadPictureRequest is null");
            Assert.notNull(request.getRelationId(), "orderId is null");
            Assert.notNull(request.getAttachmentType(), "attachmentType is null");
            Assert.notNull(request.getAttachmentName(), "attachmentName is null");
            Assert.notNull(request.getAttachmentDetailType(), "attachmentDetailType is null");
            Assert.notNull(request.getFileName(), "fileName is null");
        }catch (Exception e){
            log.error("[validateUploadParam] 参数校验失败", e);
            throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }
    }

    /**
     * 构建oss上传请求对象
     * @param request
     * @return
     */
    private OSSUploadRequest buildOSSUploadRequest(UploadAttachmentRequest request){
        OSSUploadRequest uploadRequest = new OSSUploadRequest();
        uploadRequest.setBytes(request.getBytes());
        uploadRequest.setAttachmentType(AttachmentTypeEnum.parse(request.getAttachmentType()));
        uploadRequest.setAttachmentName(request.getAttachmentName());
        uploadRequest.setAttachmentDetailType(AttachmentDetailTypeEnum.parse(request.getAttachmentDetailType()));
        uploadRequest.setFileName(request.getFileName());
        return uploadRequest;
    }


    /**
     * 构建本地附件请求对象
     * @param request
     * @param url
     * @return
     */
    private UploadAttachmentRequest buildUploadAttachmentRequest(UploadAttachmentRequest request, String url){
        UploadAttachmentRequest uploadAttachmentRequest = new UploadAttachmentRequest();
        uploadAttachmentRequest.setRelationId(request.getRelationId());
        uploadAttachmentRequest.setAttachmentType(request.getAttachmentType());
        uploadAttachmentRequest.setAttachmentName(request.getAttachmentName());
        uploadAttachmentRequest.setAttachmentDetailType(request.getAttachmentDetailType());
        uploadAttachmentRequest.setAttachmentUrl(url);
        uploadAttachmentRequest.setDataSource(AttachmentDataSourceEnum.WECHAT_MINI_PROGRAM.getValue());
        return uploadAttachmentRequest;
    }

}
