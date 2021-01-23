package com.damai.wine.common.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * 配置文件常量
 */
public class ConfigConstants {

    // 缓存前缀
    public final static String loginTokenPre = "#wechatminiprogramkey-wine#login";

    // 前后端交互token 对应在request中的key
    public final static String TOKEN_FILE = "token";

    // 后端生成的traceId，跟踪id
    public static final String TRACEID = "traceId";

    // 阿里云上的文件夹
    public static String folder;

    @Value("${aliyun.oss.folder}")
    public void setFolder(String folder) {
        ConfigConstants.folder = folder;
    }
}
