package com.common.enums.order;

import lombok.Getter;

/**
 * @author jason
 */
@Getter
public enum PayStatusEnum {
    NEW(0, "等待支付"),
    FINISHED(1, "支付成功"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
