package com.damai.wine.dao.mapper;

import com.damai.wine.dao.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 根据主键id删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    /**
     * 根据主键id查询用户
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);

    /**
     * 根据 openId 查询用户信息
     * @param openId
     * @return
     */
    User selectByOpenId(String openId);

    int updateByPrimaryKeySelective(User record);

//    int updateByPrimaryKey(User record);

    /**
     * 根据 openId 更新用户信息
     * @param record
     * @return
     */
    int updateByOpenIdSelective(User record);

}