package com.chc.api_zuul.filter;

import com.chc.api_zuul.constant.RedisConstant;
import com.chc.api_zuul.utils.CookieUtlit;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 买家端的权限控制
 * @author chc
 * @create 2019-01-11 17:17
 **/
@Component
public class AuthBuyerFilter extends ZuulFilter {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 指定拦截类型(参数拦截)
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 在指定的filter之前执行(参数的filter)
     * 越小的优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if("/order/order/create".equals(request.getRequestURI())){
            return true;
        }
        return false;
    }

    /**
     * 拦截后的逻辑处理
     * 指定get请求都必须有token参数
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        Cookie cookie = CookieUtlit.get(request, "openid");
        if(cookie == null || StringUtils.isEmpty(cookie.getValue())){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }


        return null;
    }
}
