package com.product.server.service.impl;

import com.product.server.service.ProductCategoryService;
import com.respository.ProductCategoryRepository;
import com.respository.dataObject.ProductCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jason
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired(required = false)
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategoryEntity> findByCategoryTypeIn(List<Integer> categoryTypes) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypes);
    }
}
