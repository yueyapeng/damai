package com.damai.wine.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user")
public class User {
    /**
     * 用户表主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户微信端唯一标识
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 用户户数据进行加密签名的密钥
     */
    @Column(name = "session_key")
    private String sessionKey;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户性别，1 为男性，2 为女性
     */
    private String sex;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 用户头像，可暂时不存
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 用户标识
     */
    @Column(name = "union_id")
    private String unionId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取用户表主键id
     *
     * @return id - 用户表主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置用户表主键id
     *
     * @param id 用户表主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户微信端唯一标识
     *
     * @return open_id - 用户微信端唯一标识
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置用户微信端唯一标识
     *
     * @param openId 用户微信端唯一标识
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取用户户数据进行加密签名的密钥
     *
     * @return session_key - 用户户数据进行加密签名的密钥
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * 设置用户户数据进行加密签名的密钥
     *
     * @param sessionKey 用户户数据进行加密签名的密钥
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * 获取电话号码
     *
     * @return phone - 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     *
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户昵称
     *
     * @return nick_name - 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取用户性别，1 为男性，2 为女性
     *
     * @return sex - 用户性别，1 为男性，2 为女性
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置用户性别，1 为男性，2 为女性
     *
     * @param sex 用户性别，1 为男性，2 为女性
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取用户头像，可暂时不存
     *
     * @return avatar_url - 用户头像，可暂时不存
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置用户头像，可暂时不存
     *
     * @param avatarUrl 用户头像，可暂时不存
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取用户标识
     *
     * @return union_id - 用户标识
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 设置用户标识
     *
     * @param unionId 用户标识
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}