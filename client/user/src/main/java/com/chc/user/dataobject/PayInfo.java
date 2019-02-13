package com.chc.user.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author chc
 * @create 2019-02-07 0:24
 **/
@Data
@Entity
public class PayInfo {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 订单id
     */
    @Column
    private Long orderId;

    /**
     * 状态(1:成功)
     */
    @Column
    private Integer status;

    /**
     * 支付金额
     */
    @Column
    private Integer amount;
}
