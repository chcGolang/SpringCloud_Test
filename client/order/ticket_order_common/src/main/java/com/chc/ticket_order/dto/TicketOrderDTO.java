package com.chc.ticket_order.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 订购影票
 * @author chc
 * @create 2019-02-06 22:51
 **/
@Data
public class TicketOrderDTO implements Serializable {
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
    /**
     * 状态(1:锁定影票成功,2:创建订单,3:支付成功,4:修改影票状态成功,5:完成全部流程,-1:失败)
     */
    private Integer status;
    /**
     * 状态(-1:锁票失败,-2:创建订单,-3:支付失败,-4:修改影票状态失败)
     */
    private Integer errorStatus;

    /**
     * 失败原因
     */
    private String errorInfo;
}
