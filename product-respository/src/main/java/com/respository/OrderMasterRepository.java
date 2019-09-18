package com.respository;

import com.respository.dataObject.OrderMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jason
 */
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMasterEntity, String> {
}
