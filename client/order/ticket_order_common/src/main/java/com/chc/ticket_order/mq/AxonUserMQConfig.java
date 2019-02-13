package com.chc.ticket_order.mq;

/**
 * 订票操作失败队列
 * @author chc
 * @create 2019-02-06 23:00
 **/
public interface AxonUserMQConfig {
    String EXCHANGE_NAME = TicketOrderNewMQConfig.EXCHANGE_NAME;
    String QUEUE_NAME="axon_user_queue_#";
    String ROUTING_KEY = "axon_user_routingKey_#";
}
