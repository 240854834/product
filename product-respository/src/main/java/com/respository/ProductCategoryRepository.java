package com.respository;

import com.respository.dataObject.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jason
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Integer> {

    List<ProductCategoryEntity> findByCategoryTypeIn(List<Integer> categoryTypes);
}
