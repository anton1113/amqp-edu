package com.arash.edu.amqp.init;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by anton on 01.11.18.
 *
 */
@Component
@RequiredArgsConstructor
public class AmqEntitiesInitializer {

    private final AmqpAdmin amqpAdmin;

    @PostConstruct
    private void init() {
        Exchange fooExchange = new DirectExchange("foo");
        Queue fooQueue = new Queue("foo");
        Binding fooBing = BindingBuilder.bind(fooQueue).to(fooExchange).with("foo").noargs();

        amqpAdmin.declareQueue(fooQueue);
        amqpAdmin.declareExchange(fooExchange);
        amqpAdmin.declareBinding(fooBing);
    }
}
