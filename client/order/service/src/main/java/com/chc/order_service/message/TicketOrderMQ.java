package com.chc.order_service.message;

import com.chc.order_service.dataobject.OrderTicket;
import com.chc.order_service.repository.OrderTicketRepository;
import com.chc.ticket_order.constant.TicketOrderSucceed;
import com.chc.ticket_order.dto.TicketOrderDTO;
import com.chc.ticket_order.constant.TicketOrderFailure;
import com.chc.ticket_order.mq.TicketOrderFailureMQConfig;
import com.chc.ticket_order.mq.TicketOrderFinishMQConfig;
import com.chc.ticket_order.mq.TicketOrderNewMQConfig;
import com.chc.ticket_order.mq.TicketOrderPayMQConfig;
import com.chc.ticket_order.mq.TicketOrderPlaceMQConfig;
import com.chc.ticket_order.mq.TicketOrderUnLockerMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author chc
 * @create 2019-02-06 23:04
 **/
@Slf4j
@Component
public class TicketOrderMQ {
    @Autowired
    OrderTicketRepository orderTicketRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发起锁票请求
     */
    public void sentLockTicket(TicketOrderDTO orderDTO){
        orderDTO.setUuid(UUID.randomUUID().toString());
        rabbitTemplate.setChannelTransacted(false);
        rabbitTemplate.convertAndSend(TicketOrderNewMQConfig.EXCHANGE_NAME,TicketOrderNewMQConfig.ROUTING_KEY,orderDTO);
    }

    /**
     * 生成影票订单信息
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = TicketOrderPlaceMQConfig.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            value = @Queue(value = TicketOrderPlaceMQConfig.QUEUE_NAME,durable = "true"),
            key = TicketOrderPlaceMQConfig.ROUTING_KEY
    ))
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void placeTicketOrder(@Payload TicketOrderDTO orderDTO, Message message,Channel channel)throws Exception{
        OrderTicket orderTicket = orderTicketRepository.findOneByUuid(orderDTO.getUuid());
        if(orderTicket == null){
            // 保存订单信息
            orderTicket = new OrderTicket();
            BeanUtils.copyProperties(orderDTO,orderTicket);
            orderTicket.setStatus(1);
            orderTicket.setCreateTime(ZonedDateTime.now());
            orderTicket = orderTicketRepository.save(orderTicket);
            orderDTO.setId(orderTicket.getId());
        }else {
            log.info("placeTicketOrder:订单已存在:{}",orderDTO);
        }
        orderDTO.setStatus(TicketOrderSucceed.ORDET_NEW);

        // 发送支付请求信息
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.convertAndSend(TicketOrderPayMQConfig.EXCHANGE_NAME,TicketOrderPayMQConfig.ROUTING_KEY,orderDTO);

        rabbitMqBasicAck(message, channel);

    }

    /**
     * 修改订单状态
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = TicketOrderFinishMQConfig.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            value = @Queue(value = TicketOrderFinishMQConfig.QUEUE_NAME,durable = "true"),
            key = TicketOrderFinishMQConfig.ROUTING_KEY
    ))
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void finishTicketOrder(@Payload TicketOrderDTO orderDTO, Message message,Channel channel)throws Exception{
        OrderTicket orderTicket = orderTicketRepository.findOneById(orderDTO.getId());
        orderTicket.setStatus(2);
        orderTicketRepository.save(orderTicket);
        orderDTO.setStatus(TicketOrderSucceed.FINISH);
        log.info("finishTicketOrder 完成全部流程:{}",orderDTO);
        rabbitMqBasicAck(message, channel);
    }
    /**
     * 订票错误处理
     * @param orderDTO
     * @param message
     * @param channel
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = TicketOrderFailureMQConfig.EXCHANGE_NAME),
            value = @Queue(value = TicketOrderFailureMQConfig.QUEUE_NAME,durable = "true"),
            key = TicketOrderFailureMQConfig.ROUTING_KEY
    ))
    @RabbitHandler
    public void failerTicket(@Payload TicketOrderDTO orderDTO,Message message,Channel channel)throws Exception{
        int errorStatus = orderDTO.getErrorStatus();
        OrderTicket orderTicket = findOrNewOrder(orderDTO);
        switch (errorStatus){
            case TicketOrderFailure.TICKET_LOKE_ERR :
                ticketUnLock(orderDTO);
                break;
            case TicketOrderFailure.PAY_ERR:
                ticketUnLock(orderDTO);
                break;
            case TicketOrderFailure.TICKET_STATUS_UPDATE_ERR:
                ticketUnLock(orderDTO);
                break;
            case TicketOrderFailure.ORDET_NEW_ERR:
                ticketUnLock(orderDTO);
                break;
            case TicketOrderFailure.TIME_OUT:
                ticketUnLock(orderDTO);
                break;
        }

        orderTicket.setStatus(-1);
        orderTicket.setReson(orderDTO.getErrorInfo());
        orderTicketRepository.save(orderTicket);

        rabbitMqBasicAck(message, channel);

    }

    /**
     * 解除影票锁定
     */
    private void ticketUnLock(TicketOrderDTO orderDTO){
        rabbitTemplate.convertAndSend(TicketOrderUnLockerMQConfig.EXCHANGE_NAME,TicketOrderUnLockerMQConfig.ROUTING_KEY,orderDTO);
    }

    /**
     * 查询或创建TicketOrder
     * @param orderDTO
     * @return
     */
    private OrderTicket findOrNewOrder(TicketOrderDTO orderDTO){
        OrderTicket orderTicket = new OrderTicket();
        if(orderDTO.getId() == null){
            BeanUtils.copyProperties(orderDTO,orderTicket);
            orderTicket.setCreateTime(ZonedDateTime.now());
        }else {
            orderTicket = orderTicketRepository.findOneById(orderDTO.getId());
        }
        return orderTicket;
    }

    private void rabbitMqBasicAck(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
    }

    /**
     * 检查超时订单定时器(每10秒检查一次)
     */
    @Scheduled(fixedDelay = 10000L)
    public void checkTimeoutTicketOrder(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now().minusMinutes(1L);
        List<OrderTicket> orderTickets = orderTicketRepository.findAllByStatusAndCreateTimeBefore(1, zonedDateTime);
        orderTickets.forEach(orderTicket -> {
            TicketOrderDTO orderDTO = new TicketOrderDTO();
            BeanUtils.copyProperties(orderTicket,orderDTO);
            orderDTO.setErrorStatus(TicketOrderFailure.TIME_OUT);
            orderDTO.setErrorInfo("订单超时");
            rabbitTemplate.convertAndSend(TicketOrderFailureMQConfig.EXCHANGE_NAME,TicketOrderFailureMQConfig.ROUTING_KEY,orderDTO);
        });
    }


}
