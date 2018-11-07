package com.arash.edu.amqp.msg;

import lombok.Data;

/**
 * Created by anton on 01.11.18.
 *
 */
@Data
public class FooResponse extends Response {

    private String text;
}
