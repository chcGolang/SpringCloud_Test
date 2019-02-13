package com.chc.ticket.service.controller;

import com.chc.ticket.service.dao.TicketRepository;
import com.chc.ticket.service.dto.OrderDto;
import com.chc.ticket.service.model.Ticket;
import com.chc.ticket.service.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.xml.ws.Service;

/**
 * @author chc
 * @create 2019-02-06 0:31
 **/
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketService ticketService;
    @PostConstruct
    public void init(){
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setName("Num.1");
        ticket.setTicketNum(100L);
        ticketRepository.save(ticket);
    }

    /**
     * 并发下锁票事务(不安全)
     * @param orderDto
     * @return
     */
    @PostMapping("/ticketLock")
    public Ticket ticketLock(@RequestBody OrderDto orderDto){
        return ticketService.ticketLock(orderDto);
    }

    /**
     * 并发下锁票事务(安全)
     * @param orderDto
     * @return
     */
    @PostMapping("/ticketLock2")
    public int ticketLock2(@RequestBody OrderDto orderDto){
        return ticketService.ticketLock2(orderDto);
    }


}
