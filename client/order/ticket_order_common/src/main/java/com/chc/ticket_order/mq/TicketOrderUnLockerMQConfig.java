package com.chc.ticket_order.mq;

/**
 * 锁票失败或者修改影票状态失败队列
 * @author chc
 * @create 2019-02-06 23:00
 **/
public interface TicketOrderUnLockerMQConfig {
    String EXCHANGE_NAME = TicketOrderNewMQConfig.EXCHANGE_NAME;
    String QUEUE_NAME="unLocker_ticket_order_queue";
    String ROUTING_KEY = "unLocker_ticket_order_routingKey";
}
