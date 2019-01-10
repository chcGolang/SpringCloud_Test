package com.chc.order_service.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * spring cloud stream 实现
 * @author chc
 * @create 2019-01-10 17:30
 **/
@Component
@EnableBinding(SpringCloudSteamClient.class)
@Slf4j
public class SpringCloudStreamReceiver {
    @StreamListener(SpringCloudSteamClient.OUTPUT)
    public void process(Object message){
        log.info("SpringCloudStreamReceiver.process:{}",message.toString());
    }
}
