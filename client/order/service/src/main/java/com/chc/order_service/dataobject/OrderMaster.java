package com.chc.order_service.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * order_master
 * 
 * @author chc
 * @version 1.0.0
 */
@Entity
@Table(name = "order_master")
public class OrderMaster {
    /** 版本号 */
    private static final long serialVersionUID = -7720066345756245467L;

    /** orderId */
    @Id
    @Column(name = "order_id")
    private String orderId;

    /** 买家名字 */
    @Column(name = "buyer_name")
    private String buyerName;

    /** 买家电话 */
    @Column(name = "buyer_phone")
    private String buyerPhone;

    /** 买家地址 */
    @Column(name = "buyer_address")
    private String buyerAddress;

    /** 买家微信openid */
    @Column(name = "buyer_openid")
    private String buyerOpenid;

    /** 订单总金额 */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /** 订单状态, 默认为新下单 */
    @Column(name = "order_status")
    private Integer orderStatus;

    /** 支付状态, 默认未支付 */
    @Column(name = "pay_status")
    private Integer payStatus;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 修改时间 */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取orderId
     * 
     * @return orderId
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * 设置orderId
     * 
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取买家名字
     * 
     * @return 买家名字
     */
    public String getBuyerName() {
        return this.buyerName;
    }

    /**
     * 设置买家名字
     * 
     * @param buyerName
     *          买家名字
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * 获取买家电话
     * 
     * @return 买家电话
     */
    public String getBuyerPhone() {
        return this.buyerPhone;
    }

    /**
     * 设置买家电话
     * 
     * @param buyerPhone
     *          买家电话
     */
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    /**
     * 获取买家地址
     * 
     * @return 买家地址
     */
    public String getBuyerAddress() {
        return this.buyerAddress;
    }

    /**
     * 设置买家地址
     * 
     * @param buyerAddress
     *          买家地址
     */
    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    /**
     * 获取买家微信openid
     * 
     * @return 买家微信openid
     */
    public String getBuyerOpenid() {
        return this.buyerOpenid;
    }

    /**
     * 设置买家微信openid
     * 
     * @param buyerOpenid
     *          买家微信openid
     */
    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid;
    }

    /**
     * 获取订单总金额
     * 
     * @return 订单总金额
     */
    public BigDecimal getOrderAmount() {
        return this.orderAmount;
    }

    /**
     * 设置订单总金额
     * 
     * @param orderAmount
     *          订单总金额
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * 获取订单状态, 默认为新下单
     * 
     * @return 订单状态
     */
    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * 设置订单状态, 默认为新下单
     * 
     * @param orderStatus
     *          订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取支付状态, 默认未支付
     * 
     * @return 支付状态
     */
    public Integer getPayStatus() {
        return this.payStatus;
    }

    /**
     * 设置支付状态, 默认未支付
     * 
     * @param payStatus
     *          支付状态
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     * 
     * @param createTime
     *          创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     * 
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置修改时间
     * 
     * @param updateTime
     *          修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}