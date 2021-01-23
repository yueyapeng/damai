package com.damai.wine.service.third.oauth.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 第三方：微信小程序认证结果对象
 */
@Data
public class ThirdOauthRes implements Serializable {

    private static final long serialVersionUID = -8956010338808591546L;

    /**
     * 授权用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    /**
     * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的 unionid 是唯一的。
     */
    private String unionid;

    /**
     * 普通用户昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 普通用户性别，0:未知，1 为男性，2 为女性
     */
    private int gender;

    /**
     * 普通用户个人资料填写的省份
     */
    private String province;

    /**
     * 普通用户个人资料填写的城市
     */
    private String city;

    /**
     * 国家，如中国为 CN
     */
    private String country;

    /**
     * 语言
     * en	英文
     * zh_CN	简体中文
     * zh_TW	繁体中文
     */
    private String language;

    /**
     * 错误编码
     */
    private String errcode;

    /**
     * 错误信息描述
     */
    private String errmsg;


}
