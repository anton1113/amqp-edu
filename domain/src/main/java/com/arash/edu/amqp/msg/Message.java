package com.arash.edu.amqp.msg;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Message implements Serializable {

    private int errorCode;
    private String errorText;
}
