package com.chc.eurka_server2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurkaServer2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurkaServer2Application.class, args);
    }

}

