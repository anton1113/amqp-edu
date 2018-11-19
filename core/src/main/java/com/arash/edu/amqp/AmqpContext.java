package com.arash.edu.amqp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Component("amqpContext")
@RequiredArgsConstructor
public class AmqpContext {

    private static final String SERVICE_EXCHANGE_NAME = "consumer";
    private static final String READ_TEXT_METHOD_NAME = "read_text";
    private static final String WRITE_TEXT_METHOD_NAME = "write_text";

    private final AmqpAdmin amqpAdmin;

    @Getter
    private Map<String, String> methodsMap;

    @PostConstruct
    private void init() {
        Exchange exchange = new DirectExchange(SERVICE_EXCHANGE_NAME);
        amqpAdmin.declareExchange(exchange);

        Queue readTextQueue = new Queue(generateQueueName(READ_TEXT_METHOD_NAME));
        Queue writeTextQueue = new Queue(generateQueueName(WRITE_TEXT_METHOD_NAME));
        amqpAdmin.declareQueue(readTextQueue);
        amqpAdmin.declareQueue(writeTextQueue);

        Binding readTextBinding = bind(readTextQueue).to(exchange).with(readTextQueue.getName()).noargs();
        Binding writeTextBinding = bind(writeTextQueue).to(exchange).with(writeTextQueue.getName()).noargs();
        amqpAdmin.declareBinding(readTextBinding);
        amqpAdmin.declareBinding(writeTextBinding);

        methodsMap = new HashMap<>();
        methodsMap.put(READ_TEXT_METHOD_NAME, readTextBinding.getRoutingKey());
        methodsMap.put(WRITE_TEXT_METHOD_NAME, writeTextBinding.getRoutingKey());
    }

    private String generateQueueName(String methodName) {
        return SERVICE_EXCHANGE_NAME.concat(".").concat(methodName);
    }
}
