package com.chc.ticket.service.service;

import com.chc.ticket.service.dao.TicketRepository;
import com.chc.ticket.service.dto.OrderDto;
import com.chc.ticket.service.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chc
 * @create 2019-02-06 0:27
 **/
@Slf4j
@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    /**
     * 锁定影票(事务不安全)
     * @param orderDto
     * @return
     */
    @Transactional
    public Ticket ticketLock(OrderDto orderDto){
        Ticket ticket = ticketRepository.findOneByTicketNum(orderDto.getTicketNum());
        ticket.setLockUser(orderDto.getUserId());
        ticketRepository.save(ticket);
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            log.error("sleep error:{}",e);
        }
        return ticket;
    }

    /**
     * 锁定影票(事务安全)
     * @param orderDto
     * @return
     */
    @Transactional
    public int ticketLock2(OrderDto orderDto){
        int lockTicket = ticketRepository.lockTicket(orderDto.getUserId(), orderDto.getTicketNum());
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            log.error("sleep error:{}",e);
        }
        return lockTicket;
    }
}
