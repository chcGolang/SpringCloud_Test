package com.chc.order_service.repository;


import com.chc.order_service.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chc
 * @create 2019-01-04 18:09
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

}
