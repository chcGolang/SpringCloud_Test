package com.chc.user.service.impl;

import com.chc.user.dataobject.UserInfo;
import com.chc.user.repository.UserInfoRepository;
import com.chc.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chc
 * @create 2019-01-12 16:23
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Override
    public UserInfo findByOpenid(String openId) {
        return userInfoRepository.findByOpenid(openId);
    }
}
