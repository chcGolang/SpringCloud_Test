package com.chc.order_service.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * order_detail
 * 
 * @author chc
 * @version 1.0.0
 */
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    /** 版本号 */
    private static final long serialVersionUID = 7108349504916316702L;

    /** detailId */
    @Id
    @Column(name = "detail_id")
    private String detailId;

    /** orderId */
    @Column(name = "order_id")
    private String orderId;

    /** productId */
    @Column(name = "product_id")
    private String productId;

    /** 商品名称 */
    @Column(name = "product_name")
    private String productName;

    /** 当前价格,单位分 */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /** 数量 */
    @Column(name = "product_quantity")
    private Integer productQuantity;

    /** 小图 */
    @Column(name = "product_icon")
    private String productIcon;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 修改时间 */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取detailId
     * 
     * @return detailId
     */
    public String getDetailId() {
        return this.detailId;
    }

    /**
     * 设置detailId
     * 
     * @param detailId
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

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
     * 获取productId
     * 
     * @return productId
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * 设置productId
     * 
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取商品名称
     * 
     * @return 商品名称
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * 设置商品名称
     * 
     * @param productName
     *          商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取当前价格,单位分
     * 
     * @return 当前价格
     */
    public BigDecimal getProductPrice() {
        return this.productPrice;
    }

    /**
     * 设置当前价格,单位分
     * 
     * @param productPrice
     *          当前价格
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 获取数量
     * 
     * @return 数量
     */
    public Integer getProductQuantity() {
        return this.productQuantity;
    }

    /**
     * 设置数量
     * 
     * @param productQuantity
     *          数量
     */
    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * 获取小图
     * 
     * @return 小图
     */
    public String getProductIcon() {
        return this.productIcon;
    }

    /**
     * 设置小图
     * 
     * @param productIcon
     *          小图
     */
    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
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