package com.chc.user.service;

import com.chc.user.dataobject.UserInfo;

/**
 * @author chc
 * @create 2019-01-12 16:19
 **/
public interface UserInfoService {

    /**
     * 根据openId查询用户信息
     * @param openId
     * @return
     */
    UserInfo findByOpenid(String openId);
}
