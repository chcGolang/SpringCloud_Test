package com.chc.api_zuul.enums;

import lombok.Getter;


@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),
    LOGIN_FAIL(3,"登录失败"),
    ROLE_ERROR(4,"角色权限错误")

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
