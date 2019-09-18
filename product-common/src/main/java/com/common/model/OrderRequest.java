package com.common.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jason
 */
@Data
public class OrderRequest {

    @NotNull(message = "买家姓名不能为空")
    private String name;

    private String phone;

    private String address;

    private String openid;

    private List<Product> items;

    @Data
    public static class Product {
        private String productId;

        private Integer productQuantity;
    }
}
