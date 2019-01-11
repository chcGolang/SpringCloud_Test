package com.chc.order_service.message;

import com.alibaba.fastjson.JSONObject;
import common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chc
 * @create 2019-01-10 19:19
 **/
@Component
@Slf4j
public class ProductInfoReceiver {

    private static String PRODUCT_STOCK_ID = "product_stock_%s";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
        List<ProductInfoOutput> productInfoOutputs = JSONObject.parseArray(message, ProductInfoOutput.class);
        log.info("从队列[{}]接收消息{}","productInfo",productInfoOutputs);
        productInfoOutputs.stream().forEach(productInfoOutput -> {
            String format = String.format(PRODUCT_STOCK_ID, productInfoOutput.getProductId());
            stringRedisTemplate.opsForValue().set(format,String.valueOf(productInfoOutput.getProductStock()));
        });
    }
}
