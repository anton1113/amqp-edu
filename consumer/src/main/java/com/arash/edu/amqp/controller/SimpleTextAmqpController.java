package com.arash.edu.amqp.controller;

import com.arash.edu.amqp.msg.request.SimpleActionTextRequest;
import com.arash.edu.amqp.msg.request.SimpleTextDataRequest;
import com.arash.edu.amqp.msg.response.SimpleActionTextResponse;
import com.arash.edu.amqp.msg.response.SimpleTextDataResponse;
import com.arash.edu.amqp.service.SimpleTextDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class SimpleTextAmqpController {

    private final SimpleTextDataService simpleTextDataService;

    @RabbitListener(queues = "#{amqpContext.methodsMap['text_data']}")
    public SimpleTextDataResponse processSimpleTextDataRequest(SimpleTextDataRequest request) {
        log.info(String.format("SimpleTextData request is being processed, text=%s", request.getText()));
        return simpleTextDataService.processSimpleTextDataRequest(request);
    }
//
//    @RabbitListener(queues = "#{amqpContext.methodsMap['write_text']}")
//    public SimpleActionTextResponse writeText(SimpleActionTextRequest request) {
//        return simpleTextDataService.writeSimpleText(request);
//    }
}
