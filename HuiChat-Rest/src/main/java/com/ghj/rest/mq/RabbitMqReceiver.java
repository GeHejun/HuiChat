package com.ghj.rest.mq;

import com.ghj.common.base.Constant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReceiver {





    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = Constant.EXCHANGE_A, type = ExchangeTypes.TOPIC),
                    value = @Queue(value = Constant.QUEUE_A),
                    key = Constant.ROUTING_KEY_A
            )
    )
    public void process(byte[] bytes) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
