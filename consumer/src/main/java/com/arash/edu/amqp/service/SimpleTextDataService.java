package com.arash.edu.amqp.service;

import com.arash.edu.amqp.msg.db.SimpleTexts;
import com.arash.edu.amqp.msg.request.SimpleTextDataRequest;
import com.arash.edu.amqp.msg.response.SimpleTextDataResponse;
import com.arash.edu.amqp.repository.SimpleTextsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleTextDataService {

    private static final String ID = "demo";

    private final SimpleTextsRepository simpleTextsRepository;

    public SimpleTextDataResponse processSimpleTextDataRequest(SimpleTextDataRequest request) {
        SimpleTextDataResponse response = new SimpleTextDataResponse();
        registerProcessing(request);
        response.setText(request.getText());
        return response;
    }

    private void registerProcessing(SimpleTextDataRequest request) {
        SimpleTexts simpleTexts = simpleTextsRepository.findById(ID).orElse(new SimpleTexts(ID, new ArrayList<>()));
        simpleTexts.getReceivedResponses().add(Integer.parseInt(request.getText()));
        simpleTexts.getReceivedResponses().sort(Integer::compareTo);
        checkListConsistency(simpleTexts.getReceivedResponses());
        simpleTextsRepository.save(simpleTexts);
    }

    private void checkListConsistency(List<Integer> list) {
        if (list.contains(-1)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i != list.get(i)) {
                list.add(-1);
                break;
            }
        }
    }
}
