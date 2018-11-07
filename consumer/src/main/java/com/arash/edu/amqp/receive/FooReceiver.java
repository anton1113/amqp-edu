package com.arash.edu.amqp.receive;

import com.arash.edu.amqp.msg.FooRequest;
import com.arash.edu.amqp.msg.FooResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 01.11.18.
 *
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class FooReceiver {

    @RabbitListener(queues = "#{constantsHolder.itIsAConstant}", returnExceptions = "true")
    private FooResponse foo(FooRequest request) {
        return createFooResponse(request);
    }

    private FooResponse createFooResponse(FooRequest fooRequest) {
        FooResponse fooResponse = new FooResponse();
        fooResponse.setText("Got your message " +
                fooRequest.getText() + " and add. data " + fooRequest.getAdditionalData());
        return fooResponse;
    }
}
