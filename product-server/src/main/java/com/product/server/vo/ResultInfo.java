package com.product.server.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author jason
 */
@Data
@Builder
public class ResultInfo<T> {

    private static final Integer SUCCESS = 0;
    private static final Integer FAIL = 1;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;


    public static <T> ResultInfo success(T data) {

        return ResultInfo.builder()
                .code(SUCCESS)
                .msg("成功")
                .data(data)
                .build();
    }
}
