package com.damai.wine.service.user;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.api.common.enums.ResultCode;
import com.damai.wine.api.service.request.user.UserAddRequest;
import com.damai.wine.api.service.request.user.UserUpdateRequest;
import com.damai.wine.api.service.response.user.UserDto;
import com.damai.wine.common.exception.BizException;
import com.damai.wine.dao.model.User;
import com.damai.wine.dao.repository.UserRepository;
import com.damai.wine.rpcservice.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

@Slf4j
@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 新增用户信息
     * @return
     */
    public String insertUser(UserAddRequest userAddRequest){
        if (log.isInfoEnabled()) {
            log.info("请求参数:{}", JSONObject.toJSONString(userAddRequest));
        }
        try {
            validateCreateUser(userAddRequest);

            // 生成主键id
            if (StringUtils.isBlank(userAddRequest.getId())){
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                userAddRequest.setId(id);
            }

            User user = new User();
            BeanUtils.copyProperties(userAddRequest, user);
            userRepository.addUser(user);
        } catch (IllegalArgumentException e){
            log.error("[insertUser] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        } catch (Exception e){
            log.error("[insertUser] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
        return userAddRequest.getId();
    }

    private void validateCreateUser(UserAddRequest request){
        Assert.notNull(request, "UserAddRequest is null");
        Assert.notNull(request.getOpenId(), "openId is null");
        Assert.notNull(request.getSessionKey(), "sessionKey is null");
    }

    /**
     * 根据用户主键 id 查询用户信息
     * @param id
     * @return
     */
    public UserDto queryUserById(String id){
        if (log.isInfoEnabled()) {
            log.info("请求参数:id:{}", id);
        }
        try {
            Assert.notNull(id, "id is null");
            User user = userRepository.queryByPrimaryKey(id);
            if (user != null){
                return copyProperties(user, UserDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryUserById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryUserById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     * 根据用户的微信唯一标识 openId 查询用户信息
     * @param openId
     * @return
     */
    public UserDto queryUserByOpenId(String openId){
        if (log.isInfoEnabled()) {
            log.info("请求参数:openId:{}", openId);
        }
        try {
            Assert.notNull(openId, "openId is null");
            User user = userRepository.queryByOpenId(openId);
            if (user != null){
                return copyProperties(user, UserDto.class);
            }
            return null;
        }catch (IllegalArgumentException e){
            log.error("[queryUserByOpenId] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[queryUserByOpenId] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     * 根据用户的唯一标识 openId 更新用户信息
     * @param request
     */
    public void updateUserByOpenId(UserUpdateRequest request){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(request));
        }
        try {
            Assert.notNull(request.getOpenId(), "openId is null");
            User user = new User();
            BeanUtils.copyProperties(request, user);
            userRepository.updateByOpenId(user);
        }catch (IllegalArgumentException e){
            log.error("[updateUserByOpenId] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[updateUserByOpenId] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


    /**
     * 根据用户主键 id 更新用户信息
     * @param request
     */
    public void updateUserById(UserUpdateRequest request){
        if (log.isInfoEnabled()) {
            log.info("请求参数:request:{}", JSONObject.toJSONString(request));
        }
        try {
            Assert.notNull(request.getId(), "id is null");
            User user = new User();
            BeanUtils.copyProperties(request, user);
            userRepository.updateByPrimaryKey(user);
        }catch (IllegalArgumentException e){
            log.error("[updateUserById] 参数异常", e);
            throw new BizException(ResultCode.PARAM_FAIL.getCode(), e.getMessage());
        }catch (Exception e){
            log.error("[updateUserById] 数据库操作异常", e);
            throw new BizException(ResultCode.DB_EXCEPTION.getCode(), e.getMessage());
        }
    }


}
