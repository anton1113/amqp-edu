package com.arash.edu.amqp.broker;

import com.arash.edu.amqp.CustomMessagePostProcessor;
import com.arash.edu.amqp.msg.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Log4j2
@Component
@RequiredArgsConstructor
public class MessageBroker {

    private final RabbitTemplate rabbitTemplate;
    private final CustomMessagePostProcessor customMessagePostProcessor;

    public <T extends Message, K extends Message> K send(T request, String exchange, String binding) {
        K response = (K) rabbitTemplate.convertSendAndReceive(exchange, binding, request, customMessagePostProcessor);

//        ListenableFuture<K> future = retryAsyncRabbitTemplate.convertSendAndReceive(exchange, binding, request);
//        K response = null;
//        try {
//            response = future.get();
//        } catch (InterruptedException|ExecutionException e) {
//            log.error(e);
//        }
        return response;
    }
}
