package com.damai.wine.interceptor;

import com.alibaba.fastjson.JSON;
import com.damai.wine.common.ContextDataHolder;
import com.damai.wine.common.ResponseResultCode;
import com.damai.wine.common.constants.ConfigConstants;
import com.damai.wine.common.utils.MyStringUtils;
import com.damai.wine.common.utils.PatternMatcher;
import com.damai.wine.config.AppConfig;
import com.damai.wine.domain.UserInfo;
import com.damai.wine.exception.WineException;
import com.damai.wine.service.cache.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * 登录拦截器
 * @author yueyp
 * @date 2020-12-22 下午7:50:17
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Resource
    private CacheService cacheService;

    @Autowired
    AppConfig appConfig;

    @Resource
    private PatternMatcher patternMatcher;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = ContextDataHolder.getInstance().getTraceId();

//        ContextDataHolder.getInstance().setUrl(request.getRequestURI());
        // 1.不需要登录的接口直接返回 true
        for (String url : appConfig.getAnon_login()) {
            if (patternMatcher.matches(url, getPathWithinApplication(request))) {
                // 1-1.不需要登录但是需要用户信息
                /*String token = request.getHeader(ConfigConstants.TOKEN_FILE);
                if (org.apache.commons.lang3.StringUtils.isNotBlank(token)) {
                    UserInfo user = cacheService.getFromCacheByKey(ConfigConstants.loginTokenPre + token, UserInfo.class);
                    if (user != null) {
                        ContextDataHolder.getInstance().setUserInfo(user);
                    }
                }*/
                return true;
            }
        }
        // 2.需要登录的链接，先判断请求中是否带有token
        String token = request.getHeader(ConfigConstants.TOKEN_FILE);
        if (StringUtils.isBlank(token)) {
            logger.error("{}token参数不能为空", traceId);
            throw new WineException(ResponseResultCode.USER_NOT_TOKEN);
        }
        // 3.带有token的，判断当前token对应的用户信息缓存是否还在有效期内
        UserInfo user = cacheService.getFromCacheByKey(ConfigConstants.loginTokenPre + token, UserInfo.class);
        if (user == null) {
            logger.warn("{}用户未登陆或token过期token:{}", traceId, token);
            throw new WineException(ResponseResultCode.USER_NOT_LOGIN);
        }
        ContextDataHolder.getInstance().setUserInfo(user);
//        String ip = AppUtils.getIpAddr(request);
//        ContextDataHolder.getInstance().setIp(ip);
        logger.info("{}用户{},-ip[{}]登录成功。", traceId, JSON.toJSON(user), ContextDataHolder.getInstance().getIp());
        ContextDataHolder.getInstance().setToken(token);
        cacheService.putToCache(ConfigConstants.loginTokenPre + token, user, appConfig.getLOGIN_TOKEN_TIME());
        return true;
    }

    public static String getPathWithinApplication(HttpServletRequest request) {
        String contextPath = getContextPath(request);
        String requestUri = getRequestUri(request);
        if (MyStringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
            String path = requestUri.substring(contextPath.length());
            return MyStringUtils.hasText(path) ? path : "/";
        } else {
            return requestUri;
        }
    }

    public static String getRequestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    public static String getContextPath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        if ("/".equals(contextPath)) {
            contextPath = "";
        }
        return contextPath;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        if (org.apache.commons.lang3.StringUtils.isNotBlank(ContextDataHolder.getInstance().getVersion())) {
//            response.addHeader(AppConstant.VERSION, ContextDataHolder.getInstance().getVersion());
//        }
        if (StringUtils.isNotBlank(ContextDataHolder.getInstance().getTraceId())) {
            response.addHeader(ConfigConstants.TRACEID, ContextDataHolder.getInstance().getTraceId());
        }
        if (StringUtils.isNotBlank(ContextDataHolder.getInstance().getToken())) {
            response.addHeader(ConfigConstants.TOKEN_FILE, ContextDataHolder.getInstance().getToken());
        }
//        if (org.apache.commons.lang3.StringUtils.isNotBlank(ContextDataHolder.getInstance().getCrypt())) {
//            response.addHeader(AppConstant.IS_CRYPT, ContextDataHolder.getInstance().getCrypt());
//        }

    }

}
