package com.chc.order_service.throwable;



import lombok.Data;
import lombok.Getter;

/**
 * 自定义异常(已知异常)
 * @author chc
 * @date 2017-09-12
 *
 */
@Data
public class BaseException extends RuntimeException {

	/**
	 * 错误代码
	 */
	@Getter
	private String errorCode = "6000";
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseException(String errorCode,String message){
		super(message);
		this.errorCode=errorCode;
	}
	
	public BaseException(String message){
		super(message);
		this.errorCode=null;
	}
	
	public BaseException(){
		super();
	}
	
	public BaseException(Throwable t){
		super(t);
	}

}
