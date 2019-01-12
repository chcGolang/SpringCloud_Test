package com.chc.api_zuul.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ;
    private int code;
    private String message;

    RoleEnum(int code, String message) {
        this.code=code;
        this.message=message;
    }
}
