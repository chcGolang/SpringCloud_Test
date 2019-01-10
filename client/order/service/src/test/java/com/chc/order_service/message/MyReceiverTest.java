package com.chc.order_service.message;

import com.chc.order_service.OrderServiceApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

@Component
public class MyReceiverTest extends OrderServiceApplicationTests {

    @Autowired
    AmqpTemplate amqpTemplate;
    @Test
    public void process() {
        amqpTemplate.convertAndSend("myQueue","date:"+new Date());
    }

    @Test
    public void processComputer() {
        amqpTemplate.convertAndSend("myOrder","computer","date:"+new Date());
    }

    @Test
    public void processFood() {
        amqpTemplate.convertAndSend("myOrder","food","date"+new Date());
    }
}