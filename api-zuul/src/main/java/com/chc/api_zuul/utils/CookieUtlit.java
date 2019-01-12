package com.chc.api_zuul.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chc
 * @create 2019-01-12 16:51
 **/
public class CookieUtlit {
    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

    }

    /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies==null || cookies.length == 0){
            return null;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }

        return null;

    }
}
