package com.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jason
 */
@Data
@AllArgsConstructor
public class CartInfo {

    private String productId;

    private Integer productQuantity;
}
