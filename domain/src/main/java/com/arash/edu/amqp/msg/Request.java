package com.arash.edu.amqp.msg;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by anton on 01.11.18.
 *
 */
@Data
public abstract class Request implements Cloneable, Serializable {
}
