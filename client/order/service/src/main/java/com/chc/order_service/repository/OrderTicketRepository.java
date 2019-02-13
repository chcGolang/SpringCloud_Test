package com.chc.order_service.repository;

import com.chc.order_service.dataobject.OrderTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author chc
 * @create 2019-02-06 21:14
 **/
public interface OrderTicketRepository extends JpaRepository<OrderTicket,Long> {

    List<OrderTicket> findByUserId(Long usetId);

    OrderTicket findOneByUuid(String uuid);

    OrderTicket findOneById(Long id);

    List<OrderTicket> findAllByStatusAndCreateTimeBefore(Integer status, ZonedDateTime dateTime);
}
