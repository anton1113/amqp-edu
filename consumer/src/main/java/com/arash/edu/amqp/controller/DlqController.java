package com.arash.edu.amqp.controller;

import com.arash.edu.amqp.broker.MessageBroker;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class DlqController {

    private static final String X_DEATH_HEADER = "x-death";
    private static final String TYPE_ID = "__TypeId__";

    private static final String EXCHANGE = "exchange";
    private static final String ROUTING_KEYS = "routing-keys";

    private final MessageBroker messageBroker;

    @RabbitListener(queues = "#{amqpContext.methodsMap['dlq']}")
    public void handleDeadMessage(Message message) throws ClassNotFoundException, IOException {
        log.error(String.format("DLQ received a message: %s", message.toString()));
        requeueMessage(message);
    }

    private <T extends com.arash.edu.amqp.msg.Message> void requeueMessage(Message message) throws ClassNotFoundException, IOException {
        List xDeathProperties = (ArrayList) message.getMessageProperties().getHeaders().get(X_DEATH_HEADER);
        HashMap xDeathHeadersMap = (HashMap) xDeathProperties.get(0);
        String exchange = (String) xDeathHeadersMap.get(EXCHANGE);
        String routingKey = ((List<String>) xDeathHeadersMap.get(ROUTING_KEYS)).get(0);
        T request = fetchRequest(message);
        messageBroker.send(request, exchange, routingKey);
    }

    private <T extends com.arash.edu.amqp.msg.Message> T fetchRequest(Message message) throws ClassNotFoundException, IOException {
        String className = (String) message.getMessageProperties().getHeaders().get(TYPE_ID);
        Class clazz = Class.forName(className);
        ObjectMapper mapper = new ObjectMapper();
        return (T) mapper.readValue(message.getBody(), clazz);
    }
}
