package com.chc.api_zuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * zuul的配置信息(统一配置的方式刷新)
 * @author chc
 * @create 2019-01-11 16:30
 **/
@Component
public class ZuulConfig {
    @ConfigurationProperties("zuul")
    @RefreshScope
    public ZuulProperties zuulProperties(){
        ZuulProperties zuulProperties = new ZuulProperties();
        return zuulProperties;
    }
}
