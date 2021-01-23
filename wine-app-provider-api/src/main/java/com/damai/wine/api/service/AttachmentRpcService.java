package com.damai.wine.api.service;

import com.damai.wine.api.common.Result;
import com.damai.wine.api.service.request.attachment.AttachmentAddRequest;
import com.damai.wine.api.service.request.attachment.AttachmentQueryRequest;
import com.damai.wine.api.service.response.attachment.AttachmentDto;

import java.util.List;

public interface AttachmentRpcService {

    /**
     * 附件新增
     * @param request
     * @return
     */
    Result<AttachmentDto> addAttachment(AttachmentAddRequest request);



    /**
     * 根据附件主键 id 查询附件数据
     * @param id
     * @return
     */
    Result<AttachmentDto> queryAttachmentById(String id);

    /**
     * 根据 realtionId + attachmentType 查询附件信息
     * @param realtionId
     * @param attachmentType
     * @return
     */
    Result<List<AttachmentDto>> queryAttachmentByRealtionIdAndType(String realtionId, String attachmentType);

    /**
     * 根据复合条件查询附件信息
     * @param request
     * @return
     */
    Result<List<AttachmentDto>> queryAttachmentByExample(AttachmentQueryRequest request);

}
