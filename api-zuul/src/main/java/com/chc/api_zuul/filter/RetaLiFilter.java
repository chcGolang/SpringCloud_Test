package com.chc.api_zuul.filter;

import com.chc.api_zuul.exception.RateLimiterException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 限流
 * @author chc
 * @create 2019-01-11 17:49
 **/
public class RetaLiFilter extends ZuulFilter {
    // 限流组件(每秒请求数量)
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(200);
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if(!RATE_LIMITER.tryAcquire()){
            throw new RateLimiterException();
        }
        return null;
    }
}
