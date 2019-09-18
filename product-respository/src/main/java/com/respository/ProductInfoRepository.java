package com.respository;

import com.respository.dataObject.ProductInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jason
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, String> {
    @Query("select u from ProductInfoEntity u where u.productStatus = ?1")
    List<ProductInfoEntity> findByProductStatus(int productStatus);

    @Query("select u from ProductInfoEntity u where u.productId in (?1)")
    List<ProductInfoEntity> findByProductIdIn(List<String> productIdList);
}
