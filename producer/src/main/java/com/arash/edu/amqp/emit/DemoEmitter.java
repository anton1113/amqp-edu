package com.arash.edu.amqp.emit;

import com.arash.edu.amqp.broker.MessageBroker;
import com.arash.edu.amqp.msg.request.SimpleTextDataRequest;
import com.arash.edu.amqp.msg.response.SimpleTextDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class DemoEmitter {

    private static final String CONSUMER_EXCHANGE = "consumer";
    private static final String READ_ROUTING_KEY = "consumer.text_data";

    private static long counter;

    private final MessageBroker messageBroker;

    @Scheduled(fixedRate = 5000)
    private void emitSimpleTextDataRequest() {
        SimpleTextDataResponse response = messageBroker.send(
                createReadTextRequest(), CONSUMER_EXCHANGE, READ_ROUTING_KEY);
        log.info(response);
    }

    private SimpleTextDataRequest createReadTextRequest() {
        return new SimpleTextDataRequest(String.valueOf(counter++));
    }
}
