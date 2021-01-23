package com.damai.wine.service.user;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.service.TestSupport;
import com.damai.wine.service.third.oauth.WechatMiniProgramOauthService;
import com.damai.wine.service.third.oauth.response.ThirdOauthRes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WechatMiniProgramOauthServiceTest extends TestSupport {

    @Autowired
    WechatMiniProgramOauthService wechatMiniProgramOauthService;

    @Test
    public void code2SessionTest(){
        ThirdOauthRes result = wechatMiniProgramOauthService.code2Session("1234");
        System.out.println("result:" + JSONObject.toJSONString(result));
    }


}
