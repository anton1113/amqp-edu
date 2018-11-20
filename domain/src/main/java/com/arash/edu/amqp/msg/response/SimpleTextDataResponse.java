package com.arash.edu.amqp.msg.response;

import com.arash.edu.amqp.msg.InfoMessage;
import lombok.Data;

@Data
public class SimpleTextDataResponse extends InfoMessage {

    private String text;
}
