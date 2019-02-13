package com.chc.ticket_order.axon.User.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chc
 * @create 2019-02-13 15:43
 **/
@Data
@AllArgsConstructor
public class OrderPayFailedEvent {
    private String orderId;
}
