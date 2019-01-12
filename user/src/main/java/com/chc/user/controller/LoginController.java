package com.chc.user.controller;

import com.chc.user.constant.CookieConstant;
import com.chc.user.constant.RedisConstant;
import com.chc.user.dataobject.UserInfo;
import com.chc.user.enums.ResultEnum;
import com.chc.user.enums.RoleEnum;
import com.chc.user.service.UserInfoService;
import com.chc.user.utils.CookieUtlit;
import com.chc.user.utils.ResultVOUtil;
import com.chc.user.vo.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author chc
 * @create 2019-01-12 16:28
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVo buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response, HttpServletRequest request){

        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        if(userInfo.getRole() != RoleEnum.BUYER.getCode()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        CookieUtlit.set(response,CookieConstant.openid,openid,CookieConstant.expire);

        return ResultVOUtil.success(null);
    }

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVo seller(@RequestParam("openid") String openid,
                          HttpServletResponse response,HttpServletRequest request){
        Cookie cookie = CookieUtlit.get(request, CookieConstant.TOKEN);
        if(cookie != null &&
                ! StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TIMELAMP,cookie.getValue())))){
            return ResultVOUtil.success(null);
        }

        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        if(userInfo.getRole() != RoleEnum.SELLER.getCode()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TIMELAMP,token),
                openid,
                CookieConstant.expire,
                TimeUnit.SECONDS);
        CookieUtlit.set(response,CookieConstant.TOKEN,token,CookieConstant.expire);

        return ResultVOUtil.success(null);
    }

}
