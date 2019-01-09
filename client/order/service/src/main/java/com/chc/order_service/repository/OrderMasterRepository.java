package com.chc.order_service.repository;


import com.chc.order_service.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chc
 * @create 2019-01-04 18:10
 **/
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
