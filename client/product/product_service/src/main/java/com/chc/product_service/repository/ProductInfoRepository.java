package com.chc.product_service.repository;



import com.chc.product_service.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chc
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer status);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
