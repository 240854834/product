package com.product.server.service;

import com.common.model.CartInfo;
import com.respository.dataObject.ProductInfoEntity;

import java.util.List;

/**
 * @author jason
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfoEntity> findUpAll();

    List<ProductInfoEntity> findByProductId(List<String> productIds);

    void decreaseStock(List<CartInfo> cartInfos);
}
