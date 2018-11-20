package com.arash.edu.amqp.msg.request;

import com.arash.edu.amqp.msg.InfoMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTextDataRequest extends InfoMessage {

    private String text;
}
