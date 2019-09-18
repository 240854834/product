package com.product.server.exception;

/**
 * @author jason
 */
public class ProductException extends RuntimeException {
    private static final long serialVersionUID = -222316739646372364L;

    private static final Integer FAIL = 1;

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(String message) {
        new ProductException(FAIL, message);
    }
}
