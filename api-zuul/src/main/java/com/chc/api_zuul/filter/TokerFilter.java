package com.chc.api_zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;

/**
 * token参数校验
 * @author chc
 * @create 2019-01-11 17:17
 **/
@Component
public class TokerFilter extends ZuulFilter {
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
        return true;
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
        String method = request.getMethod();
        if(!method.equals("GET")){
            return null;
        }
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            // 请求不通过
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }

        return null;
    }
}
