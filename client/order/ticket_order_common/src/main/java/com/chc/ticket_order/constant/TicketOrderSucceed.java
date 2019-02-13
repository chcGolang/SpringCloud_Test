package com.chc.ticket_order.constant;

/**
 * 订票成功流程状态
 */
public interface TicketOrderSucceed {
    // 锁定影票成功
    public static final int TICKET_LOKE = 1;
    // 支付成功
    public static final int PAY= 3;
    // 创建订单成功
    public static final int ORDET_NEW= 2;
    // 修改影票状态成功
    public static final int TICKET_STATUS_UPDATE=4;
    // 完成全部流程
    public static final int FINISH = 5;



}
