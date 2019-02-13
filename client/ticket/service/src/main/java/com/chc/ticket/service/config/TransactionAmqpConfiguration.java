package com.chc.ticket.service.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq事务组件(rabbitTemplate.setChannelTransacted(true);开启)
 * @author chc
 * @create 2019-01-27 19:03
 **/
//@Configuration
public class TransactionAmqpConfiguration {

    /*public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        SimpleRoutingConnectionFactory factory = new SimpleRoutingConnectionFactory();
        container.setTransactionManager(transactionManager(factory));
        container.setChannelTransacted(true);
        return container;
    }*/

   /* @Bean //PlatformTransactionManager
    public RabbitTransactionManager rabbitTransactionManager(ConnectionFactory configuration) {
        return new RabbitTransactionManager(configuration);
    }*/



}
