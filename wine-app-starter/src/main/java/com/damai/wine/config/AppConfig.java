package com.damai.wine.config;


import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyp
 */
@Configuration
@ConfigurationProperties(prefix = "base_auth")
@PropertySource("classpath:application.properties")
@Data
public class AppConfig {
    private static Logger log = Logger.getLogger(AppConfig.class);

    @Value("${login.token.time:72000}")
    private long LOGIN_TOKEN_TIME;// 登录时间
    private List<String> anon_login = new ArrayList<>();
//    private List<String> excludedFilter = new ArrayList<>();//不需要走filter，不需要加密

}
