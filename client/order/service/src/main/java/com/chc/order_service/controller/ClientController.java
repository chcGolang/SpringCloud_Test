package com.chc.order_service.controller;


import com.chc.order_service.dataobject.ProductInfo;
import com.chc.order_service.dto.CartDTO;
import com.chc.product_client.cilent.ProductFeignClient;
import common.DecreaseStockInput;
import common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author chc
 * @create 2019-01-05 14:48
 **/
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用Ribbon达成微服务之间的通信
     * @return
     */
    @GetMapping("/getRibbonProductMsg")
    public String getRibbonProductMsg(){
        String result;
        // 第一种方式(直接使用RestTemplate,url写死)
        /*RestTemplate restTemplate = new RestTemplate();
        result = restTemplate.getForObject("http://127.0.0.1:8081/msg",String.class);*/

        // 第二种方式(使用loadBalancerClient通过应用名称获取url,然后在使用restTemplate)
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance choose = loadBalancerClient.choose("product-client");
        String host = choose.getHost();
        int port = choose.getPort();
        String url = String.format("http://%s:%d/msg", host, port);
        result = restTemplate.getForObject(url,String.class);

        // 第三种方式(使用@LoadBalanced,可以在restTemplate里使用应用名称访问)
        // 需要在config.RestTemplateConfig注入
        //result = restTemplate.getForObject("http://product-client/msg",String.class);


        log.info("response{}",result);
        return result;
    }



    @Autowired
    private ProductFeignClient productFeignClient;
    /**
     * 使用Fegin达成微服务之间的通信
     * @return
     */
    @GetMapping("/getFeignProductMsg")
    public String getFeignProductMsg(){
        String result;
        result = productFeignClient.msg();

        log.info("response{}",result);
        return result;
    }

    @GetMapping("/getProductList")
    public String getProductList(){

        List<ProductInfoOutput> productInfos = productFeignClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068", "164103465734242707"));

        log.info("response{}",productInfos);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productFeignClient.decreaseStock(Arrays.asList(new DecreaseStockInput("157875196366160022",2)));

        return "ok";
    }
}
