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
    private static final String READ_TEXT_METHOD_NAME = "text_data";
    private static final String DLX = "dlx";
    private static final String DLQ = "dlq";
    private static final String DLX_NAME = SERVICE_EXCHANGE_NAME.concat(".").concat(DLX);
    private static final String DLQ_NAME = SERVICE_EXCHANGE_NAME.concat(".").concat(DLQ);

    private final AmqpAdmin amqpAdmin;

    @Getter
    private Map<String, String> methodsMap;

    @PostConstruct
    private void init() {
        Exchange exchange = new DirectExchange(SERVICE_EXCHANGE_NAME);
        amqpAdmin.declareExchange(exchange);

        Queue readTextQueue = createQueue(generateQueueName(READ_TEXT_METHOD_NAME));
        amqpAdmin.declareQueue(readTextQueue);

        Binding readTextBinding = bind(readTextQueue).to(exchange).with(readTextQueue.getName()).noargs();
        amqpAdmin.declareBinding(readTextBinding);

        methodsMap = new HashMap<>();
        methodsMap.put(READ_TEXT_METHOD_NAME, readTextBinding.getRoutingKey());

        declareDlEntities();
    }

    private String generateQueueName(String methodName) {
        return SERVICE_EXCHANGE_NAME.concat(".").concat(methodName);
    }

    private Queue createQueue(String name) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DLX_NAME);
        args.put("x-dead-letter-routing-key", DLQ_NAME);
        return new Queue(name, true, false, false, args);
    }

    private void declareDlEntities() {
        Exchange dlx = new DirectExchange(DLX_NAME);
        amqpAdmin.declareExchange(dlx);
        Queue queue = new Queue(DLQ_NAME);
        Binding binding = bind(queue).to(dlx).with(DLQ_NAME).noargs();
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
        methodsMap.put(DLQ, DLQ_NAME);
    }
}
