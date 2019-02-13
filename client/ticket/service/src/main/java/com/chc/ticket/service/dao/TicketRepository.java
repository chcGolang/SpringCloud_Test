package com.chc.ticket.service.dao;

import com.chc.ticket.service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chc
 * @create 2019-02-06 0:26
 **/
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByOwner(Long owner);

    Ticket findOneByTicketNum(Long ticketNum);

    // clearAutomatically 定义在执行修改查询之后是否应该清除底层持久性上下文。
    @Modifying(clearAutomatically = true)
    Ticket save(Ticket ticket);

    @Modifying
    @Query(value = "update Ticket set lockUser = :userid where lockUser is null and ticketNum = :ticketNum")
    int lockTicket(@Param("userid") Long userid ,@Param("ticketNum") Long ticketNum);

    @Modifying
    @Query(value = "update Ticket set owner = ?1, lockUser = null where lockUser = ?1 and ticketNum = ?2")
    int moveTicket( Long userid ,Long ticketNum);

    @Modifying
    @Query(value = "update Ticket set owner = null, lockUser = null where lockUser = ?1 and ticketNum = ?2")
    int unTicket( Long userid ,Long ticketNum);
}
