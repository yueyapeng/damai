package com.damai.wine.dao.repository;

import com.damai.wine.dao.mapper.UserMapper;
import com.damai.wine.dao.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRepository {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户主键查询用户信息
     * @param id
     * @return
     */
    public User queryByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据微信唯一标识查询用户信息
     * @param openId
     * @return
     */
    public User queryByOpenId(String openId) {
        return userMapper.selectByOpenId(openId);
    }

    /**
     * 新增用户信息
     * @param record
     */
    public void addUser(User record) {
        userMapper.insertSelective(record);
    }

    /**
     * 根据用户主键更改用户信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(User record){
        return userMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据用户微信唯一标识更改用户信息
     * @param record
     * @return
     */
    public int updateByOpenId(User record){
        return userMapper.updateByOpenIdSelective(record);
    }
}
