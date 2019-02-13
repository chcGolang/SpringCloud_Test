package com.chc.ticket_order.mq;

/**
 * 支付完毕修改订单状态队列
 * @author chc
 * @create 2019-02-06 23:00
 **/
public interface TicketOrderFinishMQConfig {
    String EXCHANGE_NAME = TicketOrderNewMQConfig.EXCHANGE_NAME;
    String QUEUE_NAME="finish_ticket_order_queue";
    String ROUTING_KEY = "finish_ticket_order_routingKey";
}
