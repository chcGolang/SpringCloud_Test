package com.chc.user.repository;

import com.chc.user.dataobject.PayInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chc
 * @create 2019-02-07 0:27
 **/
public interface PayInfoRepository extends JpaRepository<PayInfo,Long> {
    PayInfo findOneByOrderId(Long orderId);
}
