package com.damai.wine.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 * @author 作者:yueyp
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8397393001476646144L;

    private String id;

    /**
     * 登录账号 手机号
     */
    private String openId;

    private String sessionKey;

    /**
     * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的 unionid 是唯一的。
     */
    private String unionId;

    private String phone;

    private String nickName;

    private String sex;

    /**
     * 登录账号主键
     */
    private String province;
    /**
     * 产品id 主键唯一
     */
    private String city;
    /**
     * 国家
     */
    private String country;

    private String avatarUrl;


}
