package com.arash.edu.amqp.repository;

import com.arash.edu.amqp.model.PaymentMethod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anton on 25.07.18.
 *
 */
@Repository
public interface PaymentMethodRepository extends MongoRepository<PaymentMethod, PaymentMethod.PaymentMethodData> {

    List<PaymentMethod> findByUserId(String userId);

    @Query("{ '_id.maskedCard' : ?0, '_id.cardExpiration' : ?1 }")
    PaymentMethod findByMaskedCardAndCardExpiration(String maskedCard, String cardExpiration);

    @Query("{ '_id.maskedCard' : ?0, '_id.cardExpiration' : ?1, 'userId' : ?2 }")
    PaymentMethod findByPaymentMethodDataAndUserId(String maskedCard, String cardExpiration, String userId);
}
