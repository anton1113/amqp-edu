package com.arash.edu.amqp;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component("constantsHolder")
public class ConstantsHolder {

    private String itIsAConstant = "foo";

    @PostConstruct
    private void init() {
        String gg = "#{constantsHolder.itIsAConstant}";
    }
}
