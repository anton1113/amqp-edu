package com.arash.edu.amqp.msg.request;

import com.arash.edu.amqp.msg.ActionMessage;
import lombok.Data;

@Data
public class SimpleActionTextRequest extends ActionMessage {

    private String newText;
}
