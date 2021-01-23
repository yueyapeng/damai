package com.damai.wine.web.controller.user;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.damai.wine.common.AppResult;
import com.damai.wine.common.AppWrapResult;
import com.damai.wine.common.ContextDataHolder;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.domain.UserInfo;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.cache.CacheService;
import com.damai.wine.service.third.oauth.WechatMiniProgramOauthService;
import com.damai.wine.service.third.oauth.response.ThirdOauthRes;
import com.damai.wine.service.wrapper.user.UserServiceWrapper;
import com.damai.wine.utils.AesCbcUtil;
import com.damai.wine.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * ClassName: UserController
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    WechatMiniProgramOauthService wechatMiniProgramOauthService;

    @Autowired
    UserServiceWrapper userServiceWrapper;

    @Autowired
    CacheService cacheService;

    @ResponseBody
    @RequestMapping("/login")
    public AppResult<String> login(@PathVariable("code") String code) {
        AppResult responseResult = AppWrapResult.success();
        try {
            if (log.isInfoEnabled()){
                log.info("进入用户登录凭证校验请求code=" + code);
            }
            // 1.微信用户登录凭证校验
            ThirdOauthRes result = wechatMiniProgramOauthService.code2Session(code);
            if (result == null || !result.getErrcode().equals(ResponseResultCode.SUCCESS.getCode())){
                log.error("用户登录凭证校验失败");
                responseResult.setRetCode(result.getErrcode());
                responseResult.setRetMsg(result.getErrmsg());
                return responseResult;
            }

            // 2.生成用户信息本地缓存的key，用作前后端交互token
            String token = UUID.randomUUID().toString().replaceAll("-", "");

            // 3.缓存用户的 openId 和 session_key 信息
            UserInfo userInfo = userServiceWrapper.createUser(result);

            // 4.设置缓存信息
            setUserInfoByToken(token, userInfo);

            // 5.返回token
            responseResult.setData(token);
            return responseResult;
        }  catch (WineException error) {
            log.error("[login]请求失败,{}", JSONObject.toJSONString(error));
            responseResult.setRetCode(error.getRetCode());
            responseResult.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[login]用户登录凭证校验异常", e);
            responseResult.setRetCode(ResponseResultCode.FAILED.getCode());
            responseResult.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return responseResult;
    }

    /**
     * 验证用户开放数据信息
     * @param signature
     * @param rawData
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserInfo ")
    public AppResult<UserInfo> getUserInfo(String signature, String rawData, String iv) {
        String traceId = getTraceId();
        AppResult result = AppWrapResult.success();
        try {
            if (log.isInfoEnabled()){
                log.info("traceId:{},请求签名:signature{},rawData:{}", traceId, signature, rawData);
            }
            UserInfo userInfo = ContextDataHolder.getInstance().getUserInfo();
            String session_key = userInfo.getSessionKey();

            // 1.进行签名认证
            String signature1 = AesCbcUtil.getSha1(rawData + session_key);
            if(!signature.equals(signature1)){
                log.error("用户数据验签异常");
                result.setRetCode(ResponseResultCode.SHA_USERINFO_ERROR.getCode());
                result.setRetMsg(ResponseResultCode.SHA_USERINFO_ERROR.getMsg());
                return result;
            }

            // 2.微信用户数据解密
            decodeUserInfo(userInfo, rawData, userInfo.getSessionKey(), iv);

            // 3.先更新用户信息到数据库
            userServiceWrapper.updateUserInfoByOpenId(userInfo);

            // 4.再更新缓存中的用户信息
            setUserInfoByToken(ContextDataHolder.getInstance().getToken(), userInfo);

            // 4.返回用户信息（sessionKey 不返回）
            UserInfo userInfoResult = new UserInfo();
            BeanUtils.copyProperties(userInfo, userInfoResult);
            userInfoResult.setSessionKey(null);
            result.setData(userInfoResult);
            return result;

        }  catch (WineException error) {
            log.error("[getUserInfo]traceId:{},获取用户开发数据失败,{}", traceId, JSONObject.toJSONString(error));
            result.setRetCode(error.getRetCode());
            result.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[getUserInfo]traceId:{},获取用户开放数据异常", traceId, e);
            result.setRetCode(ResponseResultCode.FAILED.getCode());
            result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getPhoneNumber")
    public AppResult<String> getPhoneNumber(String rawData, String iv) {
        String traceId = getTraceId();
        AppResult result = AppWrapResult.success();
        try {
            if (log.isInfoEnabled()){
                log.info("[getPhoneNumber]获取手机号traceId:{},请求签名:rawData:{}", traceId, rawData);
            }
            UserInfo userInfo = ContextDataHolder.getInstance().getUserInfo();

            // 1.微信用户数据解密
            decodePhoneNumber(userInfo, rawData, userInfo.getSessionKey(), iv);

            // 2.更新缓存中的用户信息
            setUserInfoByToken(ContextDataHolder.getInstance().getToken(), userInfo);

            // 更新用户手机号到数据库
            userServiceWrapper.updatePhoneNumberByOpenId(userInfo.getOpenId(), userInfo.getPhone());

            // 4.返回用户信息（sessionKey 不返回）
            UserInfo userInfoResult = new UserInfo();
            BeanUtils.copyProperties(userInfo, userInfoResult);
            userInfoResult.setSessionKey(null);
            result.setData(userInfoResult);
            return result;
        }  catch (WineException error) {
            log.error("[getPhoneNumber]请求失败,{}", JSONObject.toJSONString(error));
            result.setRetCode(error.getRetCode());
            result.setRetMsg(error.getRetMsg());
        } catch (Exception e){
            log.error("[getPhoneNumber]用户手机号信息解密异常", e);
            result.setRetCode(ResponseResultCode.FAILED.getCode());
            result.setRetMsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }



    /**
     * 对encryptedData加密数据进行AES解密
     * @param userInfo
     * @param encryptedData
     * @param session_key
     * @param iv
     * @return
     */
    private UserInfo decodeUserInfo(UserInfo userInfo, String encryptedData, String session_key, String iv) {
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                JSONObject userInfoJSON = JSON.parseObject(result);
                userInfo.setNickName((String) userInfoJSON.get("nickName"));
                userInfo.setSex((String) userInfoJSON.get("gender"));
                userInfo.setCity((String) userInfoJSON.get("city"));
                userInfo.setProvince((String) userInfoJSON.get("province"));
                userInfo.setCountry((String) userInfoJSON.get("country"));
                userInfo.setAvatarUrl((String) userInfoJSON.get("avatarUrl"));
                userInfo.setUnionId((String) userInfoJSON.get("unionId"));
                return userInfo;
            }
            return null;
        } catch (Exception e) {
            log.error("解密用户数据异常", e);
            throw new WineException(ResponseResultCode.DECODE_USERINFO_ERROR);
        }
    }


    /**
     * 解密用户手机号
     * @param userInfo
     * @param encryptedData
     * @param session_key
     * @param iv
     * @return
     */
    private UserInfo decodePhoneNumber(UserInfo userInfo, String encryptedData, String session_key, String iv) {
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            log.info("手机号解密结果:result:{}", result);
            if (null != result && result.length() > 0) {
                JSONObject userInfoJSON = JSON.parseObject(result);
                userInfo.setPhone((String) userInfoJSON.get("phoneNumber"));
                return userInfo;
            }
            return null;
        } catch (Exception e) {
            log.error("解密用户数据异常", e);
            throw new WineException(ResponseResultCode.DECODE_USERINFO_ERROR);
        }
    }



}
