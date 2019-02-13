package com.chc.ticket_order.mq;

/**
 * 支付完毕修改影票状态队列
 * @author chc
 * @create 2019-02-06 23:00
 **/
public interface TicketOrderMoveMQConfig {
    String EXCHANGE_NAME = TicketOrderNewMQConfig.EXCHANGE_NAME;
    String QUEUE_NAME="move_ticket_order_queue";
    String ROUTING_KEY = "move_ticket_order_routingKey";
}
