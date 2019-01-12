package com.chc.order_service.service.impl;



import com.chc.order_service.dataobject.OrderDetail;
import com.chc.order_service.dataobject.OrderMaster;
import com.chc.order_service.dataobject.ProductInfo;
import com.chc.order_service.dto.CartDTO;
import com.chc.order_service.dto.OrderDTO;
import com.chc.order_service.enums.OrderStatusEnum;
import com.chc.order_service.enums.PayStatusEnum;
import com.chc.order_service.enums.ResultEnum;
import com.chc.order_service.exception.OrderException;
import com.chc.order_service.repository.OrderDetailRepository;
import com.chc.order_service.repository.OrderMasterRepository;
import com.chc.order_service.service.OrderService;
import com.chc.order_service.utils.KeyUtil;
import com.chc.product_client.cilent.ProductFeignClient;
import common.DecreaseStockInput;
import common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

       //查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productFeignClient.listForOrder(productIdList);

       //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo: productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

       //扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productFeignClient.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO finish(String orderId) {
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        if(!orderMaster.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderMaster master = orderMaster.get();
        if(OrderStatusEnum.NEW.getCode() != master.getOrderStatus()){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        master.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(master);

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetails)){
            throw new  OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
