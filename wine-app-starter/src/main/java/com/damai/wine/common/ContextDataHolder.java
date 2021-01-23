package com.damai.wine.common;

import com.damai.wine.domain.UserInfo;

import java.util.UUID;

/**
 * @author yueyp
 */
public class ContextDataHolder {
    private static ThreadLocal<ContextDataHolder> instanceHolder = new ThreadLocal<ContextDataHolder>();

    private UserInfo userInfo;

    private String traceId;

    private String token;

    private String url;

    private String ip;

    private ContextDataHolder() {

    }

    public void remove() {
        instanceHolder.remove();
    }

    public static ContextDataHolder getInstance() {
        ContextDataHolder contextDataHolder = instanceHolder.get();
        if (null == contextDataHolder) {
            contextDataHolder = new ContextDataHolder();
            String traceId = UUID.randomUUID().toString().replaceAll("-", "");
            contextDataHolder.setTraceId(traceId);
            instanceHolder.set(contextDataHolder);
        }
        return contextDataHolder;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
