package com.chc.order_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author chc
 * @create 2019-01-05 15:28
 **/
@Component
public class RestTemplateConfig {

    @Bean
    @LoadBalanced //自动装配LoadBalancerClient (RibbonLoadBalancerClient)
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
