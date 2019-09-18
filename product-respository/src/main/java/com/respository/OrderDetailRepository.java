package com.respository;

import com.respository.dataObject.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jason
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, String> {
}
