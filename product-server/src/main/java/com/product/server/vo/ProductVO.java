package com.product.server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author jason
 */
@Data
@Builder
public class ProductVO {

    @JsonProperty("foods")
    List<ProductInfo> productInfoList;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
}
