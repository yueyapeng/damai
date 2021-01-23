package com.damai.wine.service.user;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.domain.UserInfo;
import com.damai.wine.service.TestSupport;
import com.damai.wine.service.third.oauth.response.ThirdOauthRes;
import com.damai.wine.service.wrapper.user.UserServiceWrapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceWrapperTest extends TestSupport {


    @Autowired
    UserServiceWrapper userServiceWrapper;

    @Test
    public void testAddUser(){
        ThirdOauthRes result = new ThirdOauthRes();
        result.setOpenid("202012270001");
        result.setSession_key("testaaaaaaaaaa");
        UserInfo userInfo = userServiceWrapper.createUser(result);
        System.out.println("============" + JSONObject.toJSONString(userInfo));
    }

    @Test
    public void testUpdateUser(){

        UserInfo userInfo = new UserInfo();
        userInfo.setNickName("Band");
        userInfo.setSex("1");
        userInfo.setCity("zh_CN");
        userInfo.setProvince("Hangzhou");
        userInfo.setCountry("CN");
        userInfo.setAvatarUrl("http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0");
        userInfo.setUnionId("1253124512612641461461");
        userInfo.setOpenId("202012270001");
        userServiceWrapper.updateUserInfoByOpenId(userInfo);
    }



    @Test
    public void testUpdatePhoneNumberByOpenId(){
        boolean result = userServiceWrapper.updatePhoneNumberByOpenId("202012270001", "15157140313");
        System.out.println(result + "============");
    }

    public static void main(String[] args) {

    }

}
