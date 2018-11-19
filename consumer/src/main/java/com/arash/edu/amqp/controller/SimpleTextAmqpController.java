package com.arash.edu.amqp.controller;

import com.arash.edu.amqp.msg.request.SimpleActionTextRequest;
import com.arash.edu.amqp.msg.request.SimpleInfoTextRequest;
import com.arash.edu.amqp.msg.response.SimpleActionTextResponse;
import com.arash.edu.amqp.msg.response.SimpleInfoTextResponse;
import com.arash.edu.amqp.service.SimpleTextReadWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleTextAmqpController {

    private final SimpleTextReadWriteService simpleTextReadWriteService;

    @RabbitListener(queues = "#{amqpContext.methodsMap['read_text']}")
    public SimpleInfoTextResponse readText(SimpleInfoTextRequest request) {
        return simpleTextReadWriteService.readSimpleText(request);
    }

    @RabbitListener(queues = "#{amqpContext.methodsMap['write_text']}")
    public SimpleActionTextResponse writeText(SimpleActionTextRequest request) {
        return simpleTextReadWriteService.writeSimpleText(request);
    }
}
