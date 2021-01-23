package com.damai.wine.service.wrapper.user;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.service.request.user.UserAddRequest;
import com.damai.wine.api.service.request.user.UserUpdateRequest;
import com.damai.wine.api.service.response.user.UserDto;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.domain.UserInfo;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.BaseService;
import com.damai.wine.service.third.oauth.response.ThirdOauthRes;
import com.damai.wine.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceWrapper extends BaseService {

    @Autowired
    UserService userService;

    /**
     * 创建用户信息
     * 1.根据 openId 查询数据库是否已存在用户信息
     * 2.如果用户信息已存在，直接返回
     * 3.如果用户信息不存在，创建用户
     * @param result
     * @return
     */
    public UserInfo createUser(ThirdOauthRes result){
        log.info("创建用户，user:{}", JSONObject.toJSONString(result));
        try {
            UserDto user = userService.queryUserByOpenId(result.getOpenid());
            if (user != null){
                // 更新用户的 sessionKey （sessionKey可能会发生变更失效）
                UserUpdateRequest updateUser = new UserUpdateRequest();
                updateUser.setOpenId(result.getOpenid());
                updateUser.setSessionKey(result.getSession_key());
                userService.updateUserByOpenId(updateUser);
                user.setSessionKey(result.getSession_key());
                return copyProperties(user, UserInfo.class);
            }
            UserInfo userInfo = buildUserInfo(result.getOpenid(), result.getSession_key());
            String id = userService.insertUser(copyProperties(userInfo, UserAddRequest.class));
            userInfo.setId(id);
            return userInfo;
        }catch (WineException e){
            log.error("创建用户异常，user:{}", JSONObject.toJSONString(result), e);
            throw e;
        }catch (Exception e){
            log.error("创建用户异常，user:{}", JSONObject.toJSONString(result), e);
            throw new WineException(ResponseResultCode.ADD_USER_ERROR);
        }
    }

    /**
     * 根据 openId 更新用户信息
     * @param userInfo
     * @return
     */
    public void updateUserInfoByOpenId(UserInfo userInfo){
        log.info("更新用户信息，userInfo:{}", JSONObject.toJSONString(userInfo));
        if (userInfo == null || StringUtils.isBlank(userInfo.getOpenId())){
            throw new WineException(ResponseResultCode.PARAM_FAIL);
        }
        try {
            UserUpdateRequest user = copyProperties(userInfo, UserUpdateRequest.class);
            userService.updateUserByOpenId(user);
        }catch (WineException e){
            log.error("更新用户信息异常，userInfo:{}", JSONObject.toJSONString(userInfo), e);
            throw e;
        }catch (Exception e){
            log.error("更新用户信息异常，userInfo:{}", JSONObject.toJSONString(userInfo), e);
            throw new WineException(ResponseResultCode.UPDATE_USER_ERROR);
        }
    }

    /**
     * 根据 openId 更新用户手机号信息
     * @param openId
     * @param phoneNumber
     * @return
     */
    public boolean updatePhoneNumberByOpenId(String openId, String phoneNumber){
        log.info("更新用户手机号信息，openId:{}, phoneNumber:{}", openId, phoneNumber);
        if (StringUtils.isBlank(openId) || StringUtils.isBlank(phoneNumber)){
            throw new WineException(ResponseResultCode.PARAM_FAIL);
        }
        try {
            UserUpdateRequest user = new UserUpdateRequest();
            user.setOpenId(openId);
            user.setPhone(phoneNumber);
            userService.updateUserByOpenId(user);
            return true;
        }catch (WineException e){
            log.error("更新用户手机号信息异常，openId:{}, phoneNumber:{}", openId, phoneNumber, e);
            throw e;
        }catch (Exception e){
            log.error("更新用户手机号信息异常，openId:{}, phoneNumber:{}", openId, phoneNumber, e);
            throw new WineException(ResponseResultCode.UPDATE_USER_ERROR);
        }
    }

    private UserInfo buildUserInfo(String openId, String sessionKey){
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId(openId);
        userInfo.setSessionKey(sessionKey);
        return userInfo;
    }

}
