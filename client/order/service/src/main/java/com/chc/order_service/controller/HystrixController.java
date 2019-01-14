package com.chc.order_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.chc.order_service.throwable.BaseException;
import com.chc.product_client.cilent.ProductFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * hystrix功能测试
 * @author chc
 * @create 2019-01-13 16:50
 **/
@RestController
// 指定默认的降级方法
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
public class HystrixController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductFeignClient productFeignClient;


    // HystrixProperty的相关填写的内容在HystrixCommandProperties中
    /* 除 HystrixBadRequestException和ignoreExceptions指定的异常外的所有异常，会统计异常次数、触发回退方法和短路逻辑。
     * Hystrix异常说明地址https://blog.csdn.net/an88411980/article/details/80552964
     */

    /*@HystrixCommand(
            // 指定降级的方法
            fallbackMethod = "fallbackMethod"
            // 指定不进行降级的异常
            ,ignoreExceptions=BaseException.class
            ,commandProperties = {
            // 请求超时时间控制(单位:毫秒,默认1秒)
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
        }
    )*/

    /*@HystrixCommand(commandProperties = {
            // 开启熔断
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 断路器的最小请求数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            // 熔断的时间(单位:毫秒)
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            // 断路器打开的错误百分比
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })*/

    // 可以使用配置的方式
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(Integer num) {
        if(num % 2 == 0){
            return "success";
        }
        String result = restTemplate.postForObject("http://PRODUCT/product/listForOrder",
                Arrays.asList("157875196366160022"), String.class);


        /* 异常控制案例
        throw  new BaseException("不触发fallback的异常");
        throw new HystrixBadRequestException("不触发fallback的异常");
        throw new RuntimeException("触发fallback的异常");*/

       /* try {
            // 请求超时的案例
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return result;
    }



    private String fallbackMethod(Throwable e){

        return "fallbackMethod";
    }

    private String defaultFallback(){
        return "defaultFallback";
    }
}
