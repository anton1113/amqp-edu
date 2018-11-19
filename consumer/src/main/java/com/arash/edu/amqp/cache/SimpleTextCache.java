package com.arash.edu.amqp.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SimpleTextCache {

    private String text;
}
