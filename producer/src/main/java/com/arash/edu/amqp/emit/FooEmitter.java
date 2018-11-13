package com.arash.edu.amqp.emit;

import com.arash.edu.amqp.broker.MessageBroker;
import com.arash.edu.amqp.msg.FooRequest;
import com.arash.edu.amqp.msg.FooResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
@Component
@RequiredArgsConstructor
public class FooEmitter {

    private final MessageBroker messageBroker;

    private int counter;

    @Scheduled(fixedRate = 5000)
    private void emitFooMessage() {
        log.info("Sending a message #" + ++counter);
        FooResponse response = messageBroker.sendAsync(createFooRequest(), "foo", "foo");
        log.info("Response text: " + response.getText());
    }

    private FooRequest createFooRequest() {
        FooRequest request = new FooRequest();
        request.setText("It is an async request #" + counter);
        request.setAdditionalData(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return request;
    }
}
