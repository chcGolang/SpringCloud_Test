package com.chc.order_service.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
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
    @SendTo(SpringCloudSteamClient.INPUT) //回收消息
    public String process(String message){
        log.info("SpringCloudStreamReceiver.process:{}",message);

        return "received";
    }

    /**
     * 接收回送的消息
     * @param message
     */
    @StreamListener(SpringCloudSteamClient.INPUT)
    public void processInput(String message){

        log.info("SpringCloudStreamReceiver.processInput:{}",message);
    }

}
