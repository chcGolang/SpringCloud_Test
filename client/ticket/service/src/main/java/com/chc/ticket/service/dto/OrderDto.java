package com.chc.ticket.service.dto;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author chc
 * @create 2019-02-06 21:46
 **/
@Data
public class OrderDto {
    private Long id;

    private String uuid;

    /**
     * 购买用户
     */
    private Long userId;
    /**
     * 购买序号
     */
    private Long ticketNum;

    private String title;
    private Integer amount;
}
