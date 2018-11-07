package com.arash.edu.amqp.msg;

import lombok.Data;

/**
 * Created by anton on 01.11.18.
 *
 */
@Data
public class FooRequest extends Request {

    private String text;
    private String additionalData;
}
