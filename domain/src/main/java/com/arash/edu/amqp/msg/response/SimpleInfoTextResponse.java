package com.arash.edu.amqp.msg.response;

import com.arash.edu.amqp.msg.InfoMessage;
import lombok.Data;

@Data
public class SimpleInfoTextResponse extends InfoMessage {

    private String currentText;
}
