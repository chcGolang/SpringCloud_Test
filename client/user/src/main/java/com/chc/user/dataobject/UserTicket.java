package com.chc.user.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 影票业务的用户
 * @author chc
 * @create 2019-02-06 21:24
 **/
@Data
@Entity(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String userName;
    @Column
    private String password;
    /**
     * 余额
     */
    @Column
    private Integer deposit;
}
