package com.damai.wine.rpcservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.Result;
import com.damai.wine.api.common.WrapResult;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.service.UserRpcService;
import com.damai.wine.api.service.request.user.UserAddRequest;
import com.damai.wine.api.service.request.user.UserUpdateRequest;
import com.damai.wine.api.service.response.user.UserDto;
import com.damai.wine.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * 提供对外的dubbo服务
 */
@Slf4j
@Service
public class UserRpcServiceImpl extends BaseService implements UserRpcService {


    @Autowired
    UserService userService;

    @Override
    public Result<UserDto> addUser(UserAddRequest request) {

        if (log.isInfoEnabled()){
            log.info("[addUser] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<UserDto> result = WrapResult.success();
        try {
            validateCreateUser(request);
            String id = userService.insertUser(request);
            request.setId(id);
            result.setData(copyProperties(request, UserDto.class));
        }catch (IllegalArgumentException e){
            log.error("[addUser] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[addUser] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<UserDto> updateUserById(UserUpdateRequest request) {
        if (log.isInfoEnabled()){
            log.info("[updateUserById] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<UserDto> result = WrapResult.success();
        try {
            Assert.notNull(request, "UserUpdateRequest is null");
            Assert.notNull(request.getId(), "id is null");
            userService.updateUserById(request);
            result.setData(copyProperties(request, UserDto.class));
        }catch (IllegalArgumentException e){
            log.error("[updateUserById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[updateUserById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<UserDto> updateUserByOpenId(UserUpdateRequest request) {
        if (log.isInfoEnabled()){
            log.info("[updateUserByOpenId] 请求参数 request:{}", JSONObject.toJSONString(request));
        }
        Result<UserDto> result = WrapResult.success();
        try {
            Assert.notNull(request, "UserUpdateRequest is null");
            Assert.notNull(request.getOpenId(), "openId is null");
            userService.updateUserByOpenId(request);
            result.setData(copyProperties(request, UserDto.class));
        }catch (IllegalArgumentException e){
            log.error("[updateUserByOpenId] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[updateUserByOpenId] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<UserDto> queryUserById(String id) {
        if (log.isInfoEnabled()){
            log.info("[queryUserById] 请求参数 id:{}", id);
        }
        Result<UserDto> result = WrapResult.success();
        try {
            Assert.notNull(id, "id is null");
            UserDto userDto = userService.queryUserById(id);
            if (userDto != null){
                result.setData(userDto);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryUserById] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryUserById] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    @Override
    public Result<UserDto> queryUserByOpenId(String openId) {
        if (log.isInfoEnabled()){
            log.info("[queryUserByOpenId] 请求参数 openId:{}", openId);
        }
        Result<UserDto> result = WrapResult.success();
        try {
            Assert.notNull(openId, "openId is null");
            UserDto userDto = userService.queryUserByOpenId(openId);
            if (userDto != null){
                result.setData(userDto);
            }
        }catch (IllegalArgumentException e){
            log.error("[queryUserByOpenId] 参数有误", e.getMessage());
            result.setRetCode(ResultCode.PARAM_FAIL.getCode());
            result.setRetMsg(e.getMessage());
        }catch (Exception e){
            log.error("[queryUserByOpenId] 操作异常", e.getMessage());
            result.setRetCode(ResultCode.FAILED.getCode());
            result.setRetMsg(ResultCode.FAILED.getMsg());
        }
        return result;
    }

    private void validateCreateUser(UserAddRequest request){
        Assert.notNull(request, "UserAddRequest is null");
        Assert.notNull(request.getOpenId(), "openId is null");
        Assert.notNull(request.getSessionKey(), "sessionKey is null");
    }

}
