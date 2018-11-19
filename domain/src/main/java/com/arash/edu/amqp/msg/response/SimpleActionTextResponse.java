package com.arash.edu.amqp.msg.response;

import com.arash.edu.amqp.msg.ActionMessage;
import lombok.Data;

@Data
public class SimpleActionTextResponse extends ActionMessage {

    private String oldText;
    private String newText;
}
