package com.product.server.service;

import com.respository.dataObject.ProductCategoryEntity;

import java.util.List;

/**
 * @author jason
 */
public interface ProductCategoryService {

    List<ProductCategoryEntity> findByCategoryTypeIn(List<Integer> categoryTypes);
}
