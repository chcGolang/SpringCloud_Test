package com.chc.ticket.service.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 影票
 * @author chc
 * @create 2019-02-06 0:10
 **/
@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 名称
     */
    @Column
    private String name;
    /**
     * 归属的用户
     */
    @Column
    private Long owner;
    /**
     *
     */
    @Column
    private Long lockUser;
    /**
     * 序号
     */
    @Column
    private Long ticketNum;
}
