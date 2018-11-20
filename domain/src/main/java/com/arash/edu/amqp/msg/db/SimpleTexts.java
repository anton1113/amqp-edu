package com.arash.edu.amqp.msg.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTexts {

    @Id
    private String id;
    private List<Integer> receivedResponses;
}
