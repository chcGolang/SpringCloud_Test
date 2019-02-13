package com.chc.ticket_order.mq;

/**
 * 创建订单请求队列
 * @author chc
 * @create 2019-02-06 23:00
 **/
public interface TicketOrderPlaceMQConfig {
    String EXCHANGE_NAME = TicketOrderNewMQConfig.EXCHANGE_NAME;
    String QUEUE_NAME="place_ticket_order_queue";
    String ROUTING_KEY = "place_ticket_order_routingKey";
}
