package com.chc.user.repository;

import com.chc.user.dataobject.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author chc
 * @create 2019-02-07 0:07
 **/
public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
    UserTicket findOneById(Long id);

    /**
     * 扣款操作
     * @param amount
     * @param userId
     * @return
     */
    @Modifying
    @Query(value = "update user_ticket set deposit = deposit - ?1 where id = ?2")
    int charge(Integer amount,Long userId);
}
