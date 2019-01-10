package com.chc.order_service.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * spring cloud stream 接口
 * @author chc
 * @create 2019-01-10 17:28
 **/
public interface SpringCloudSteamClient {
    public static String OUTPUT = "myMessageOutPut";
    public static String INPUT = "myMessageInput";


    @Input("myMessageInput")
    SubscribableChannel input();

    @Output("myMessageOutPut")
    MessageChannel output();

}
