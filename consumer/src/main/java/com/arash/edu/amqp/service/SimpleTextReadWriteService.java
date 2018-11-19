package com.arash.edu.amqp.service;

import com.arash.edu.amqp.cache.SimpleTextCache;
import com.arash.edu.amqp.msg.request.SimpleActionTextRequest;
import com.arash.edu.amqp.msg.request.SimpleInfoTextRequest;
import com.arash.edu.amqp.msg.response.SimpleActionTextResponse;
import com.arash.edu.amqp.msg.response.SimpleInfoTextResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleTextReadWriteService {

    private final SimpleTextCache simpleTextCache;

    public SimpleInfoTextResponse readSimpleText(SimpleInfoTextRequest request) {
        return createReadResponse();
    }

    public SimpleActionTextResponse writeSimpleText(SimpleActionTextRequest request) {
        String oldText = simpleTextCache.getText();
        simpleTextCache.setText(request.getNewText());
        return createWriteResponse(oldText, request.getNewText());
    }

    private SimpleInfoTextResponse createReadResponse() {
        SimpleInfoTextResponse response = new SimpleInfoTextResponse();
        response.setCurrentText(simpleTextCache.getText());
        return response;
    }

    private SimpleActionTextResponse createWriteResponse(String oldText, String newText) {
        SimpleActionTextResponse response = new SimpleActionTextResponse();
        response.setOldText(oldText);
        response.setNewText(newText);
        return response;
    }
}
