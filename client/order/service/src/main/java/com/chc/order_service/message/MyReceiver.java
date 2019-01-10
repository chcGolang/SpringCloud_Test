package com.chc.order_service.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq使用案例
 * @author chc
 * @create 2019-01-10 16:51
 **/
@Component
@Slf4j
public class MyReceiver {

    // @RabbitListener(queues = "myQueue") // 无法自动创建队列
    // @RabbitListener(queuesToDeclare = @Queue("myQueue")) // 会自动创建队列
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange(name = "myExchange")
    )) // 自动创建,Exchange和Queue绑定
    public void process(String message){
        log.info("MyReceiver.process:{}",message);
    }


    /**
     * 电脑订单消息队列
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computerQueue"),
            key = "computer",
            exchange = @Exchange(name = "myOrder")
    )) // 自动创建,Exchange和Queue绑定
    public void processComputer(String message){
        log.info("MyReceiver.processComputer:{}",message);
    }

    /**
     * 食物订单消息队列
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("foodQueue"),
            key = "food",
            exchange = @Exchange(name = "myOrder")
    )) // 自动创建,Exchange和Queue绑定
    public void processFood(String message){
        log.info("MyReceiver.processFood:{}",message);
    }




}

