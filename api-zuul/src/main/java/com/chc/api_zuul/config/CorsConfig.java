package com.chc.api_zuul.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


/**
 * 跨域请求配置
 * @author chc
 * @create 2019-01-12 18:48
 **/
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
       final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 是否支持cookie跨域
        corsConfiguration.setAllowCredentials(true);
        // 支持哪些原始域 如:www.a.com
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        // 允许跨域的头
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        // 允许请求的类型(GET,POST...)
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        // 缓存时间(在指定时间段里相同跨域的请求就不在验证)
        corsConfiguration.setMaxAge(300L);

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(urlBasedCorsConfigurationSource);
        return corsFilter;
    }
}
