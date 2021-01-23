package com.damai.wine.service.third.oss;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.common.constants.ConfigConstants;
import com.damai.wine.common.enums.AttachmentTypeEnum;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.third.oss.request.OSSUploadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
public class AliyunOssService {

    public static final String FILE_PATH = "%s/%s.%s";


    public static final int EXPIRES = 24;

    private static final String FOLDER = ConfigConstants.folder;

    /**
     * 文件上传
     * @param uploadRequest
     * @return
     */
    public String upload(OSSUploadRequest uploadRequest){

        if (log.isInfoEnabled()) {
            log.info("[upload]请求参数:uploadRequest{}", JSONObject.toJSONString(uploadRequest));
        }

        try {
            // 1.参数校验
            validateUploadParam(uploadRequest);

            InputStream stream = new ByteArrayInputStream(uploadRequest.getBytes());
            String fileName = uploadRequest.getFileName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

            // 2.根据文件类型和后缀获得一个唯一的 objectName ，后续根据 objectName 可以获得 oss 上的唯一路径
            String objectName = getObjectName(uploadRequest.getAttachmentType(), suffix);

            // 3.调用 oss 上传图片
            return OssUtils.uploadObjectByInputStream(stream, FOLDER, objectName);

        } catch (IllegalArgumentException e){
            log.error("[upload] 参数异常", e);
            throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), e.getMessage());
        } catch (Exception e){
            log.error("[upload] 调用外部服务异常", e);
            throw new WineException(ResponseResultCode.OSS_SYSTEM_ERROR.getCode(), e.getMessage());
        }
    }

    /**
     * 根据文件的 key 获取对应文件的字节流
     * @param key
     * @return
     */
    public byte[] downloadFileByteByKey(String key){
        if (log.isInfoEnabled()) {
            log.info("[downloadFileByteByKey]请求参数:key{}", key);
        }
        try {
            // 1.参数校验
            Assert.notNull(key, "key is null");

            // 3.调用 oss 上传图片
            return OssUtils.getFileByteByKey(key);
        } catch (IllegalArgumentException e){
            log.error("[downloadFileByteByKey] 参数异常", e);
            throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), e.getMessage());
        } catch (Exception e){
            log.error("[downloadFileByteByKey] 调用外部服务异常", e);
            throw new WineException(ResponseResultCode.OSS_SYSTEM_ERROR.getCode(), e.getMessage());
        }

    }

    /**
     * 根据文件的 key 获取对应文件的访问URL
     * @param key
     * @return
     */
    public String downloadFileUrlByKey(String key){
        if (log.isInfoEnabled()) {
            log.info("[downloadFileUrlByKey]请求参数:key{}", key);
        }
        try {
            // 1.参数校验
            Assert.notNull(key, "key is null");

            // 2.调用 oss 获取图片的 url ，有效期内url有效
            return OssUtils.getFileUrl(key, EXPIRES);
        } catch (IllegalArgumentException e){
            log.error("[downloadFileUrlByKey] 参数异常", e);
            throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), e.getMessage());
        } catch (Exception e){
            log.error("[downloadFileUrlByKey] 调用外部服务异常", e);
            throw new WineException(ResponseResultCode.OSS_SYSTEM_ERROR.getCode(), e.getMessage());
        }
    }


    /**
     * 阿里云 oss 图片上传参数校验
     * @param request
     */
    private void validateUploadParam(OSSUploadRequest request){
        Assert.notNull(request, "OSSUploadRequest is null");
        Assert.notNull(request.getBytes(), "bytes is null");
        Assert.notNull(request.getAttachmentType(), "attachmentType is null");
        Assert.notNull(request.getAttachmentName(), "attachmentName is null");
        Assert.notNull(request.getAttachmentDetailType(), "attachmentDetailType is null");
        Assert.notNull(request.getFileName(), "fileName is null");
    }

    /**
     * 根据传入的文件类型获得一个唯一的 objectName ，根据该字段可以查询 oss 上真正的 url 地址
     * @param fileType
     * @param suffix
     * @return
     */
    public static String getObjectName(AttachmentTypeEnum fileType, String suffix) {
        log.info("get path 入参  filetype {} suffix {}  ", fileType.getDesc(), suffix);
        return String.format(FILE_PATH, fileType.getValue(), UUID.randomUUID().toString().replaceAll("-", ""), suffix);
    }

}
