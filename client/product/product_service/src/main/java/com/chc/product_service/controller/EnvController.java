package com.chc.product_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/env")
@RefreshScope
public class EnvController {
    @Value("${env}")
    private String env;

    /**
     * 查看加载统一配置类型
     * @return
     */
    @GetMapping("/print")
    public String print() {
        return env;
    }
}
