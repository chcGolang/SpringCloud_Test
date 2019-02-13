package com.chc.ticket_order.mq;

/**
 * 订票操作失败队列
 * @author chc
 * @create 2019-02-06 23:00
 **/
public interface TicketOrderFailureMQConfig {
    String EXCHANGE_NAME = TicketOrderNewMQConfig.EXCHANGE_NAME;
    String QUEUE_NAME="failure_ticket_order_queue";
    String ROUTING_KEY = "failure_ticket_order_routingKey";
}
