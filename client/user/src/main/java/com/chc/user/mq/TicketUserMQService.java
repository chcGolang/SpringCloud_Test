package com.chc.user.mq;

import com.chc.ticket_order.constant.TicketOrderFailure;
import com.chc.ticket_order.constant.TicketOrderSucceed;
import com.chc.ticket_order.dto.TicketOrderDTO;
import com.chc.ticket_order.mq.TicketOrderFailureMQConfig;
import com.chc.ticket_order.mq.TicketOrderMoveMQConfig;
import com.chc.ticket_order.mq.TicketOrderPayMQConfig;
import com.chc.user.dataobject.PayInfo;
import com.chc.user.dataobject.UserTicket;
import com.chc.user.repository.PayInfoRepository;
import com.chc.user.repository.UserTicketRepository;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author chc
 * @create 2019-02-07 0:08
 **/
@Slf4j
@Component
public class TicketUserMQService {

    @Autowired
    UserTicketRepository userTicketRepository;
    @Autowired
    PayInfoRepository payInfoRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initUser(){
        UserTicket userTicket = new UserTicket();
        userTicket.setDeposit(100);
        userTicket.setId(1L);
        userTicket.setPassword("222");
        userTicket.setUserName("chc");
        userTicketRepository.save(userTicket);
    }

    /**
     * 接收支付请求
     * @param orderDTO
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = TicketOrderPayMQConfig.EXCHANGE_NAME),
            value = @Queue(value = TicketOrderPayMQConfig.QUEUE_NAME,durable = "true"),
            key = TicketOrderPayMQConfig.ROUTING_KEY
    ))
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void ticketPayMq(@Payload TicketOrderDTO orderDTO, Message message, Channel channel)throws Exception{
        log.info("ticketPayMq 接收支付请求:{}",orderDTO);
        PayInfo payInfo = payInfoRepository.findOneByOrderId(orderDTO.getId());
        if(payInfo != null){
            // 已经支付过了
            log.info("ticketPayMq 已经支付过了");
        }else {
            UserTicket userTicket = userTicketRepository.findOneById(orderDTO.getUserId());
            if(userTicket.getDeposit() < orderDTO.getAmount()){
                // 余额不足
                orderDTO.setErrorStatus(TicketOrderFailure.PAY_ERR);
                orderDTO.setErrorInfo("余额不足");
                rabbitTemplate.convertAndSend(TicketOrderFailureMQConfig.EXCHANGE_NAME,TicketOrderFailureMQConfig.ROUTING_KEY,orderDTO);
                rabbitAck(message, channel);
                return;

            }
            payInfo = new PayInfo();
            payInfo.setOrderId(orderDTO.getId());
            payInfo.setAmount(orderDTO.getAmount());
            payInfo.setStatus(1);
            payInfoRepository.save(payInfo);
            userTicketRepository.charge(orderDTO.getAmount(),orderDTO.getUserId());
        }

        orderDTO.setStatus(TicketOrderSucceed.PAY);
        rabbitTemplate.convertAndSend(TicketOrderMoveMQConfig.EXCHANGE_NAME,TicketOrderMoveMQConfig.ROUTING_KEY,orderDTO);

        rabbitAck(message, channel);
    }

    private void rabbitAck(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
    }
}
