package com.chc.product_service.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * user_info
 * 
 * @author chc
 * @version 1.0.0
 */
@Entity
@Table(name = "user_info")
public class UserInfo {
    /** 版本号 */
    private static final long serialVersionUID = -4577490617772484156L;

    /** id */
    @Id
    @Column(name = "id")
    private String id;

    /** username */
    @Column(name = "username")
    private String username;

    /** password */
    @Column(name = "password")
    private String password;

    /** 微信openid */
    @Column(name = "openid")
    private String openid;

    /** 1买家2卖家 */
    @Column(name = "role")
    private Integer role;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 修改时间 */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取id
     * 
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取username
     * 
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 设置username
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取password
     * 
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置password
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取微信openid
     * 
     * @return 微信openid
     */
    public String getOpenid() {
        return this.openid;
    }

    /**
     * 设置微信openid
     * 
     * @param openid
     *          微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取1买家2卖家
     * 
     * @return 1买家2卖家
     */
    public Integer getRole() {
        return this.role;
    }

    /**
     * 设置1买家2卖家
     * 
     * @param role
     *          1买家2卖家
     */
    public void setRole(Integer role) {
        this.role = role;
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