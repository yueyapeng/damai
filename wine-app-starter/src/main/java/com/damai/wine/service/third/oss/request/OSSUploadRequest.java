package com.damai.wine.service.third.oss.request;

import com.damai.wine.common.enums.AttachmentDetailTypeEnum;
import com.damai.wine.common.enums.AttachmentTypeEnum;
import lombok.Data;

@Data
public class OSSUploadRequest {

    /**
     * 文件内容
     */
    private byte[] bytes;

    /**
     * 文件类型
     */
    private AttachmentTypeEnum attachmentType;

    private String attachmentName;

    /**
     * 文件详细类型
     */
    private AttachmentDetailTypeEnum attachmentDetailType;

    /**
     * 文件名
     */
    private String fileName;


}
