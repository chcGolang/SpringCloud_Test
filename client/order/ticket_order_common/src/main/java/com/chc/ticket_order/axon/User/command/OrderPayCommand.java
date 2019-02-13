package com.chc.ticket_order.axon.User.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author chc
 * @create 2019-02-13 15:42
 **/
@Data
@AllArgsConstructor
public class OrderPayCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private String userId;
    private Double amount;
}
