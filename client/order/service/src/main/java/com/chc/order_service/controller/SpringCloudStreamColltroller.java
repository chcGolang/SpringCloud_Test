package com.chc.order_service.controller;

import com.chc.order_service.message.SpringCloudSteamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author chc
 * @create 2019-01-10 17:34
 **/
@RestController
public class SpringCloudStreamColltroller {

    @Autowired
    private SpringCloudSteamClient springCloudSteamClient;

    @GetMapping("/sendStreamMessage")
    public void sendStreamMessage(){
        springCloudSteamClient.output().send(MessageBuilder.withPayload("date:"+new Date()).build());
    }
}
