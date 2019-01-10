package com.chc.config_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.chc.config_service.utils.HttpClientUtils;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

/**
 * @author chc
 * @create 2019-01-10 15:43
 **/
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Value("${server.port}")
    private int port;
    @PostMapping(value = "/push",consumes="application/json")
    public String webhook(){
        String url = String.format("http://127.0.0.1:%d/actuator/bus-refresh", port);
        JSONObject jsonObject = HttpClientUtils.httpPost(url, new JSONObject());
        return jsonObject.toJSONString();
    }
}
