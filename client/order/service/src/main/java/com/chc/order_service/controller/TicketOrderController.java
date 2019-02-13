package com.chc.order_service.controller;

import com.chc.order_service.message.TicketOrderMQ;
import com.chc.ticket_order.dto.TicketOrderDTO;
import com.chc.ticket_order.mq.TicketOrderNewMQConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 影票购买发起
 * @author chc
 * @create 2019-02-07 1:10
 **/
@RestController
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    @Autowired
    TicketOrderMQ ticketOrderMQ;
    /**
     * 发起锁票请求
     */
    @PostMapping("/sentLockTicket")
    public String sentLockTicket(@RequestBody TicketOrderDTO orderDTO){
        ticketOrderMQ.sentLockTicket(orderDTO);
        return "发送成功";
    }


}
