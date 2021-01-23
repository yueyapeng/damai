package com.damai.wine.api.service;

import com.damai.wine.api.common.Result;
import com.damai.wine.api.service.request.user.UserAddRequest;
import com.damai.wine.api.service.request.user.UserUpdateRequest;
import com.damai.wine.api.service.response.user.UserDto;

public interface UserRpcService {

    /**
     * 用户新增
     * @param request
     * @return
     */
    Result<UserDto> addUser(UserAddRequest request);

    /**
     * 根据用户主键 id 更新用户信息
     * @param request
     * @return
     */
    Result<UserDto> updateUserById(UserUpdateRequest request);

    /**
     * 根据用户的 openId 更新用户信息
     * @param request
     * @return
     */
    Result<UserDto> updateUserByOpenId(UserUpdateRequest request);

    /**
     * 根据用户主键 id 更新用户数据
     * @param id
     * @return
     */
    Result<UserDto> queryUserById(String id);

    /**
     * 根据用户微信的唯一标识 openId 更新用户信息
     * @param openId
     * @return
     */
    Result<UserDto> queryUserByOpenId(String openId);

}
