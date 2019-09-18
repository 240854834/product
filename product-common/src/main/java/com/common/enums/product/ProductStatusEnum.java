package com.common.enums.product;

import lombok.Getter;

/**
 * @author jason
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String name;

    ProductStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
