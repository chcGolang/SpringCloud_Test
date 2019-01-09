package com.chc.product_service.vo;

import lombok.Data;

/**
 * @author chc
 * @create
 **/
@Data
public class ResultVo<T> {

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据内容
     */
    private T data;

    public static <T> ResultVo<T> Success(T data){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setData(data);
        resultVo.setMsg("成功");
        return resultVo;
    }
}
