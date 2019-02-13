package com.chc.ticket.service.service;

import com.chc.ticket.service.dao.TicketRepository;
import com.chc.ticket_order.constant.TicketOrderFailure;
import com.chc.ticket_order.constant.TicketOrderSucceed;
import com.chc.ticket_order.dto.TicketOrderDTO;
import com.chc.ticket_order.mq.TicketOrderFailureMQConfig;
import com.chc.ticket_order.mq.TicketOrderFinishMQConfig;
import com.chc.ticket_order.mq.TicketOrderMoveMQConfig;
import com.chc.ticket_order.mq.TicketOrderNewMQConfig;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chc
 * @create 2019-02-06 23:22
 **/
@Slf4j
@Service
public class TciketMQService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 接收锁票信息
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = TicketOrderNewMQConfig.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            value = @Queue(value = TicketOrderNewMQConfig.QUEUE_NAME,durable = "true"),
            key = TicketOrderNewMQConfig.ROUTING_KEY
    ))
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void lockTicketMQ(@Payload TicketOrderDTO orderDTO, Message message, Channel channel)throws Exception{
        log.info("lockTicketMQ 接收锁票信息:{}",orderDTO);
        int count = ticketRepository.lockTicket(orderDTO.getUserId(), orderDTO.getTicketNum());
        if(count == 1){
            // 锁票成功,发送下单操作信息
            orderDTO.setStatus(TicketOrderSucceed.TICKET_LOKE);
            rabbitTemplate.setChannelTransacted(true);
            rabbitTemplate.convertAndSend(TicketOrderPlaceMQConfig.EXCHANGE_NAME,TicketOrderPlaceMQConfig.ROUTING_KEY,orderDTO);
        }else {
            // 锁票失败,发送下单操作信息
            orderDTO.setErrorStatus(TicketOrderFailure.TICKET_LOKE_ERR);
            orderDTO.setErrorInfo("锁票失败");
            rabbitTemplate.setChannelTransacted(true);
            rabbitTemplate.convertAndSend(TicketOrderFailureMQConfig.EXCHANGE_NAME,TicketOrderFailureMQConfig.ROUTING_KEY,orderDTO);
        }
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
    }

    /**
     * 支付成功-修改影票状态
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = TicketOrderMoveMQConfig.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
            value = @Queue(value = TicketOrderMoveMQConfig.QUEUE_NAME,durable = "true"),
            key = TicketOrderMoveMQConfig.ROUTING_KEY
    ))
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void updateTicketStatusMQ(@Payload TicketOrderDTO orderDTO, Message message, Channel channel)throws Exception{
        log.info("updateTicketStatusMQ 支付成功修改锁表状态:{}",orderDTO);
        int count = ticketRepository.moveTicket(orderDTO.getUserId(), orderDTO.getTicketNum());
        if(count == 0){
            log.info("updateTicketStatusMQ 已经修改过状态:{}",orderDTO);
        }
        orderDTO.setStatus(TicketOrderSucceed.TICKET_STATUS_UPDATE);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
        rabbitTemplate.convertAndSend(TicketOrderFinishMQConfig.EXCHANGE_NAME,TicketOrderFinishMQConfig.ROUTING_KEY,orderDTO);

    }

    /**
     * 影票操作失败回滚操作
     */
    @Transactional
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = TicketOrderUnLockerMQConfig.EXCHANGE_NAME),
            value = @Queue(value = TicketOrderUnLockerMQConfig.QUEUE_NAME,durable = "true"),
            key = TicketOrderUnLockerMQConfig.ROUTING_KEY
    ))
    @RabbitHandler
    public void ticketUnLock(@Payload TicketOrderDTO orderDTO,Message message,Channel channel)throws Exception{
        ticketRepository.unTicket(orderDTO.getUserId(), orderDTO.getTicketNum());
        log.info("解除影票锁定:{}",orderDTO);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
    }
}
