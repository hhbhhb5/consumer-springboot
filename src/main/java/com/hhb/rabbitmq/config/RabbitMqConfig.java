package com.hhb.rabbitmq.config;

import com.hhb.rabbitmq.constant.RabbitMqConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname RabbitMqConfig
 * @Description TODO
 * @Date 2021/10/23 21:59
 * @Created by hhb
 */
@Configuration
public class RabbitMqConfig {
    //1。声明交换机
    @Bean("bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(RabbitMqConstant.EXCHANGE_NAME).durable(true).build();
    }
    //2.声明队列
    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(RabbitMqConstant.QUEUE_NAME).build();
    }
    //3.队列与交换机进行绑定
    public Binding bindQueueExchange(@Qualifier("bootQueue") Queue queue,@Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}
