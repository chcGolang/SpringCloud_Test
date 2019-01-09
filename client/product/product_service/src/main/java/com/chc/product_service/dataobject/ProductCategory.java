package com.chc.product_service.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * product_category
 * 
 * @author chc
 * @version 1.0.0
 */
@Entity
@Table(name = "product_category")
public class ProductCategory {
    /** 版本号 */
    private static final long serialVersionUID = -3014500592176714095L;

    /** categoryId */
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Integer categoryId;

    /** 类目名字 */
    @Column(name = "category_name")
    private String categoryName;

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
     * 获取categoryId
     * 
     * @return categoryId
     */
    public Integer getCategoryId() {
        return this.categoryId;
    }

    /**
     * 设置categoryId
     * 
     * @param categoryId
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取类目名字
     * 
     * @return 类目名字
     */
    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * 设置类目名字
     * 
     * @param categoryName
     *          类目名字
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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