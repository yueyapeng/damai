package com.damai.wine.common.constants;

import java.nio.charset.Charset;

public interface BaseConstants {
    // UTF-8字符集
    public final static String CHARSET_UTF8         = "UTF-8";
    public final static Charset DEFAULT_CHARSET = Charset.forName(CHARSET_UTF8);

    /** 登录用户信息 */
    public static final String USER_SESSION_KEY = "accountSession";
    public static final String BACK_SLASH = "/";
    public static final String QUERY_CONNECT = "?";
    public static final String PARAM_CONNECT = "&";
    public static final String EQUAL = "=";
    public static final String COMMA = ",";

    /***** 系统标识 ********/
    public static final String SYSTEM_NAME = "loan-bpm";

    public static final String EMAIL_SEND_NAME = "loanBpm";

    /***http相关 ****/
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String URL_APPEND = "?";
    public static final String PARAM_APPEND = "&";

    String END_DATE_PATTERN = " 23:59:59";
}
