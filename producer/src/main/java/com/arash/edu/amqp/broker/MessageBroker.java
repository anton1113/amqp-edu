package com.arash.edu.amqp.broker;

import com.arash.edu.amqp.msg.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Log4j2
@Component
@RequiredArgsConstructor
public class MessageBroker {

    private final AsyncRabbitTemplate asyncRabbitTemplate;

    public <T extends Message, K extends Message> K sendAsync(T request, String exchange, String binding) {
        ListenableFuture<K> future = asyncRabbitTemplate.convertSendAndReceive(exchange, binding, request);
        K response = null;
        try {
            response = future.get();
        } catch (InterruptedException|ExecutionException e) {
            log.error(e);
        }
        return response;
    }
}
