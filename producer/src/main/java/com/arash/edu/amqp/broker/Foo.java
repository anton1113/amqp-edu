package com.arash.edu.amqp.broker;

import com.arash.edu.amqp.model.PaymentMethod;
import com.arash.edu.amqp.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by anton on 02.11.18.
 *
 */
@Component
@RequiredArgsConstructor
public class Foo {

    private final PaymentMethodRepository paymentMethodRepository;
    @Qualifier("userMongoTemplate")
    private final MongoTemplate userMongoTemplate;

    @PostConstruct
    private void init() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        List<PaymentMethod> paymentMethods1 = userMongoTemplate.findAll(PaymentMethod.class);
        System.out.println("wow");
    }
}
