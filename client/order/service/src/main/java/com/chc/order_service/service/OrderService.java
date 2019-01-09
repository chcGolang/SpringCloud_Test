package com.chc.order_service.service;


import com.chc.order_service.dto.OrderDTO;

public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
