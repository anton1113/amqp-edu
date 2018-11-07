package com.arash.edu.amqp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by anton on 25.07.18.
 *
 */
@Data
@Document(collection = "payment_method")
public class PaymentMethod {

    @Id
    private PaymentMethodData paymentMethodData;
    private PaymentMethodDetails paymentMethodDetails;
    private String userId;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentMethodData implements Cloneable, Serializable {
        private String maskedCard;
        private String cardExpiration;
    }

    @Data
    public static class PaymentMethodDetails implements Cloneable, Serializable {
        private String bin;
        private String brand;
        private String countryCode;
        private String countryName;
        private String bankName;
        private String cardType;
        private Boolean prepaid;
    }
}
