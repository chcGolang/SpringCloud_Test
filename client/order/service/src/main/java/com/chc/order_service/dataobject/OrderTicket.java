package com.chc.order_service.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/**
 * 影票订单
 * @author chc
 * @create 2019-02-06 21:05
 **/
@Data
@Entity(name = "order_ticket")
public class OrderTicket {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String uuid;
    /**
     * 购买人
     */
    @Column
    private Long userId;
    /**
     * 购买序号
     */
    @Column
    private Long ticketNum;
    @Column
    private String title;
    @Column
    private Integer amount;
    /**
     * 状态(1:新建,2:成功完成)
     */
    @Column
    private Integer status;

    /**
     * 出错原因
     */
    @Column
    private String reson;

    /**
     * 创建时间
     */
    @Column
    private ZonedDateTime createTime;

}
