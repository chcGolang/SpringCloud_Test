package com.chc.ticket_order.mq;

/**
 * 支付请求队列
 * @author chc
 * @create 2019-02-06 23:00
 **/
public interface TicketOrderPayMQConfig {
    String EXCHANGE_NAME = TicketOrderNewMQConfig.EXCHANGE_NAME;
    String QUEUE_NAME="pay_ticket_order_queue";
    String ROUTING_KEY = "pay_ticket_order_routingKey";
}
