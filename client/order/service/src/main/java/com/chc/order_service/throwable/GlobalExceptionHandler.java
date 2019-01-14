package com.chc.order_service.throwable;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局统一异常处理
 * @author chc
 * @date 2017-09-12
 *
 */
@SuppressWarnings("rawtypes")
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 已知异常处理
	 * @author chc
	 * @date 2017-09-12
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = BaseException.class)
    @ResponseBody
	public String baseExceptionErrorHandler(HttpServletRequest req, BaseException e){
		return e.getMessage();
	}
}
