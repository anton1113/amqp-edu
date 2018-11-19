package com.arash.edu.amqp.emit;

import com.arash.edu.amqp.broker.MessageBroker;
import com.arash.edu.amqp.msg.request.SimpleActionTextRequest;
import com.arash.edu.amqp.msg.request.SimpleInfoTextRequest;
import com.arash.edu.amqp.msg.response.SimpleActionTextResponse;
import com.arash.edu.amqp.msg.response.SimpleInfoTextResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class SimpleTextProducer {

    private static final String CONSUMER_EXCHANGE = "consumer";
    private static final String READ_ROUTING_KEY = "consumer.read_text";
    private static final String WRITE_ROUTING_KEY = "consumer.write_text";

    private final MessageBroker messageBroker;

    @Scheduled(fixedRate = 1000)
    private void emitSimpleReadTextRequest() {
        SimpleInfoTextResponse response = messageBroker.sendAsync(
                createReadTextRequest(), CONSUMER_EXCHANGE, READ_ROUTING_KEY);
        log.info(response);
    }

    @Scheduled(fixedRate = 5000)
    private void emitSimpleWriteTextRequest() {
        SimpleActionTextResponse response = messageBroker.sendAsync(
                createWriteTextRequest(), CONSUMER_EXCHANGE, WRITE_ROUTING_KEY);
        log.info(response);
    }

    private SimpleInfoTextRequest createReadTextRequest() {
        return new SimpleInfoTextRequest();
    }

    private SimpleActionTextRequest createWriteTextRequest() {
        SimpleActionTextRequest request = new SimpleActionTextRequest();
        String randomString = RandomStringUtils.random(10);
        request.setNewText(randomString);
        return request;
    }
}
