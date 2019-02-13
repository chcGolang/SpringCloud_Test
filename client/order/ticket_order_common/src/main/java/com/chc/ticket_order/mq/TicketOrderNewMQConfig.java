package com.chc.ticket_order.mq;

/**
 * 锁票请求队列
 * @author chc
 * @create 2019-02-06 23:00
 **/
public interface TicketOrderNewMQConfig {
    String EXCHANGE_NAME = "order_ticket_exchange";
    String QUEUE_NAME="lock_ticket_queue";
    String ROUTING_KEY = "lock_ticket_routingKey";
}
