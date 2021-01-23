package com.damai.wine.web.controller;

import com.damai.wine.common.AppResult;
import com.damai.wine.common.ContextDataHolder;
import com.damai.wine.common.PageResult;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.common.constants.BaseConstants;
import com.damai.wine.common.constants.ConfigConstants;
import com.damai.wine.domain.UserInfo;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.cache.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * @author yueyp
 * @date 2020年12月15日:下午2:17:07
 * @detail
 */
@ControllerAdvice
@RestController
@Slf4j
public class BaseController {

    // 默认用户信息缓存 2 个小时
    private static final Long CACHE_TIME = 7200L;

    private static final long serialVersionUID = 1L;

    private String filePath;

    private final int maxImageSize = 1024 * 1024;                      // 最大图片大小

    protected Map<String, Object> session;

    @Autowired
    CacheService cacheService;

    /**
     * 获取 traceId 追踪号
     * @return
     */
    protected String getTraceId(){
        return ContextDataHolder.getInstance().getTraceId();
    }


    /**
     * 设置用户缓存信息
     * @param token
     * @param basicUserInfo
     */
    public void setUserInfoByToken(String token, UserInfo basicUserInfo) {
        cacheService.putToCache(ConfigConstants.loginTokenPre + token, basicUserInfo, CACHE_TIME);
        log.info("token{}", ConfigConstants.loginTokenPre + token);
    }

    /**
     * 初始化分布，从session中取出用户帐号信息
     */
    protected UserInfo getUserAccount() {
        //登陆用户
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (UserInfo) requestAttributes.getAttribute(BaseConstants.USER_SESSION_KEY, RequestAttributes.SCOPE_SESSION);
    }

    @ExceptionHandler(value = {WineException.class})
    public AppResult bpmExceptionHandle(Exception e) {
        log.info("业务处理异常:"+e.getMessage());
        return new AppResult(ResponseResultCode.BUSINESS_ERROR.getCode(),e.getMessage());
    }


    @ExceptionHandler(value = { Exception.class})
    public AppResult globalExceptionHandle(Exception e) {
        log.error("系统异常", e);
        return new AppResult(ResponseResultCode.SYSTEM_ERROR.getCode(),"系统异常");
    }

    public AppResult successful(){
        return new AppResult<>(ResponseResultCode.SUCCESS);
    }

    public AppResult successful(PageResult pageResult){
        AppResult result = new AppResult<>(ResponseResultCode.SUCCESS);
        result.setData(pageResult.getRecords());
        result.setTotal(pageResult.getTotal());
        return result;
    }

    public AppResult successful(Object data){
        AppResult result = new AppResult<>(ResponseResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public AppResult failed(ResponseResultCode resultCode){
        return new AppResult<>(resultCode);
    }

    public AppResult failed(String code, String message){
        return new AppResult<>(code, message);
    }

    public AppResult failed(String message){
        return new AppResult<>(ResponseResultCode.SYSTEM_ERROR.getCode(), message);
    }
}
