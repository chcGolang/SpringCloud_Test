package com.chc.ticket_order.constant;

/**
 * 订票失败流程状态
 */
public interface TicketOrderFailure {

    // 锁票失败
    public static final int TICKET_LOKE_ERR = -1;
    // 支付失败
    public static final int PAY_ERR=-3;
    // 创建订单
    public static final int ORDET_NEW_ERR= -2;
    // 修改影票状态失败
    public static final int TICKET_STATUS_UPDATE_ERR=-4;
    // 超时
    public static final int TIME_OUT = -5;





}
