package com.product.server.exception;

/**
 * @author jason
 */
public class OrderException extends RuntimeException {
    private static final long serialVersionUID = 8803580939938491894L;

    private static final Integer FAIL = 1;

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(String message) {
        super(message);
        this.code = FAIL;
    }
}
