package com.chc.order_service.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * product_info
 * 
 * @author chc
 * @version 1.0.0
 */
@Entity
@Table(name = "product_info")
public class ProductInfo {
    /** 版本号 */
    private static final long serialVersionUID = 4360767586221677490L;

    /** productId */
    @Id
    @Column(name = "product_id")
    private String productId;

    /** 商品名称 */
    @Column(name = "product_name")
    private String productName;

    /** 单价 */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /** 库存 */
    @Column(name = "product_stock")
    private Integer productStock;

    /** 描述 */
    @Column(name = "product_description")
    private String productDescription;

    /** 小图 */
    @Column(name = "product_icon")
    private String productIcon;

    /** 商品状态,0正常1下架 */
    @Column(name = "product_status")
    private Integer productStatus;

    /** 类目编号 */
    @Column(name = "category_type")
    private Integer categoryType;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 修改时间 */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取单价
     * 
     * @return 单价
     */
    public BigDecimal getProductPrice() {
        return this.productPrice;
    }

    /**
     * 设置单价
     * 
     * @param productPrice
     *          单价
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 获取库存
     * 
     * @return 库存
     */
    public Integer getProductStock() {
        return this.productStock;
    }

    /**
     * 设置库存
     * 
     * @param productStock
     *          库存
     */
    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    /**
     * 获取描述
     * 
     * @return 描述
     */
    public String getProductDescription() {
        return this.productDescription;
    }

    /**
     * 设置描述
     * 
     * @param productDescription
     *          描述
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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
     * 获取商品状态,0正常1下架
     * 
     * @return 商品状态
     */
    public Integer getProductStatus() {
        return this.productStatus;
    }

    /**
     * 设置商品状态,0正常1下架
     * 
     * @param productStatus
     *          商品状态
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 获取类目编号
     * 
     * @return 类目编号
     */
    public Integer getCategoryType() {
        return this.categoryType;
    }

    /**
     * 设置类目编号
     * 
     * @param categoryType
     *          类目编号
     */
    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
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