package com.chc.order_service.VO;

import lombok.Data;


@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
