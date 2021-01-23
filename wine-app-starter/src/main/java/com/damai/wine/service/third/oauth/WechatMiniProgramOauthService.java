package com.damai.wine.service.third.oauth;

import com.alibaba.fastjson.JSONObject;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.common.constants.ConfigConstants;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.BaseService;
import com.damai.wine.service.third.oauth.response.RequestResult;
import com.damai.wine.service.third.oauth.response.ThirdOauthRes;
import com.damai.wine.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 微信小程序授权服务
 */
@Service
public class WechatMiniProgramOauthService extends BaseService {

    protected Logger logger = LoggerFactory.getLogger(WechatMiniProgramOauthService.class);

    private static final int SUCC_CODE = 200;

    @Value("${wechat.app.id}")
    private String APP_ID;

    @Value("${wechat.app.secret}")
    private String APP_SECRET;

    @Value("${wechat.app.code.2.session.url}")
    private String CODE_2_SESSION;

    @Value("${wechat.app.userinfo.url}")
    private String USERINFO_URL;

    /**
     * 微信登录凭证校验
     * @param code
     * @return
     */
    public ThirdOauthRes code2Session(String code) {
        if (logger.isInfoEnabled()) {
            logger.info("[code2Session]请求参数:code:{}", code);
        }

        ThirdOauthRes result = new ThirdOauthRes();
        try {
            // 1.校验参数
            if (StringUtils.isBlank(code)) {
                throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), "code is null");
            }

            // 2.请求url拼接  https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code
            StringBuilder url = new StringBuilder();
            url.append(CODE_2_SESSION).append("appid=").append(APP_ID).append("&secret=").
                    append(APP_SECRET).append("&js_code=").append(code).append("&grant_type=").append("authorization_code");

            // 3.调用第三方服务
            RequestResult httpResult = doGet(url.toString());

            if (logger.isInfoEnabled()) {
                logger.info("[code2Session]Http请求结果:{}", JSONObject.toJSONString(httpResult));
            }

            // 4.解析调用结果
            return analyzingHttpResult(httpResult);

        } catch (WineException error) {
            logger.error("[getAccessToken]请求失败,{}", JSONObject.toJSONString(error));
            result.setErrcode(error.getRetCode());
            result.setErrmsg(error.getRetMsg());
        } catch (Exception e) {
            logger.error("[getAccessToken]请求异常,{}", e);
            result.setErrcode(ResponseResultCode.FAILED.getCode());
            result.setErrmsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }

    /**
     * 获取用户信息
     *
     * @param access_token
     * @param openid
     * @return
     */
    public ThirdOauthRes getOauthUserInfo(String access_token, String openid) {
        if (logger.isInfoEnabled()) {
            logger.info("[getOauthUserInfo]请求参数:openid:{},access_token:{}", openid, access_token);
        }

        ThirdOauthRes result = new ThirdOauthRes();
        try {

            // 1.校验参数
            if (StringUtils.isBlank(access_token)) {
                throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), "access_token is null");
            }
            if (StringUtils.isBlank(openid)) {
                throw new WineException(ResponseResultCode.PARAM_FAIL.getCode(), "openid is null");
            }

            // 2.请求url拼接  https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
            StringBuilder url = new StringBuilder();
            url.append(USERINFO_URL).append("access_token=").append(access_token).append("&openid=").append(openid);

            // 3.调用第三方服务
            RequestResult httpResult = doGet(url.toString());

            if (logger.isInfoEnabled()) {
                logger.info("[getOauthUserInfo]Http请求结果:{}", JSONObject.toJSONString(httpResult));
            }

            // 4.解析调用结果
            return analyzingHttpResult(httpResult);

        } catch (WineException error) {
            logger.error("[getOauthUserInfo]请求失败,{}", JSONObject.toJSONString(error));
            result.setErrcode(error.getRetCode());
            result.setErrmsg(error.getRetMsg());
        } catch (Exception e) {
            logger.error("[getOauthUserInfo]请求异常,{}", e);
            result.setErrcode(ResponseResultCode.FAILED.getCode());
            result.setErrmsg(ResponseResultCode.FAILED.getMsg());
        }
        return result;
    }

    /**
     * http 调用
     *
     * @param url
     * @return
     * @throws WineException
     */
    private RequestResult doGet(String url) throws WineException {
        RequestResult httpResult = null;
        try {
            httpResult = HttpUtil.doGet(url);
            return httpResult;
        } catch (Exception e) {
            logger.warn("{}调用微信授权异常,返回结果为:{},e:", url, JSONObject.toJSONString(httpResult), e);
            throw new WineException(ResponseResultCode.INTERFACE_SERVICE_ERROR.getCode(), "http调用异常");
        }
    }

    /**
     * 解析http调用结果
     *
     * @param httpResult
     * @return
     */
    private ThirdOauthRes analyzingHttpResult(RequestResult httpResult) {
        ThirdOauthRes result = new ThirdOauthRes();
        if (StringUtils.isBlank(httpResult.getResult())) {
            logger.info("Http返回结果为空");
            result.setErrcode(ResponseResultCode.INTERFACE_SERVICE_ERROR.getCode());
            result.setErrmsg("http调用返回结果为空");
            return result;
        }
        result = JSONObject.parseObject(httpResult.getResult(), ThirdOauthRes.class);
        if (SUCC_CODE != httpResult.getStatusCode()) {
            logger.info("{}HTTP状态不为成功200:[{}]", JSONObject.toJSONString(result));
            result.setErrcode(ResponseResultCode.INTERFACE_SERVICE_ERROR.getCode());
            result.setErrmsg(result.getErrmsg());
            return result;
        }

        // 判断errcode的值
        if (StringUtils.isNotBlank(result.getErrcode())) {
            if ("0".equals(result.getErrcode())) {
                result.setErrcode(ResponseResultCode.SUCCESS.getCode());
            } else if ("-1".equals(result.getErrcode())) {
                result.setErrcode(ResponseResultCode.SYSTEM_BUSY_TRY_AGAIN_LATER.getCode());
                result.setErrmsg(ResponseResultCode.SYSTEM_BUSY_TRY_AGAIN_LATER.getMsg());
            } else if ("40029".equals(result.getErrcode())) {
                result.setErrcode(ResponseResultCode.ILLEGALITY_ARGUMENT_ERROR.getCode());
                result.setErrmsg(ResponseResultCode.ILLEGALITY_ARGUMENT_ERROR.getMsg());
            } else if ("45011".equals(result.getErrcode())) {
                result.setErrcode(ResponseResultCode.TRIES_LIMIT.getCode());
                result.setErrmsg(ResponseResultCode.TRIES_LIMIT.getMsg());
            }
        } else {
            result.setErrcode(ResponseResultCode.INTERFACE_SERVICE_ERROR.getCode());
            result.setErrmsg(result.getErrmsg());
        }
        return result;
    }

}
