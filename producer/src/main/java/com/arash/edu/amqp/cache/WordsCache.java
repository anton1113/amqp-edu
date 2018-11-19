package com.arash.edu.amqp.cache;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Log4j2
@Component
public class WordsCache {

    private static final Random RANDOM = new Random();

    private static final String WORDS_PATH = "words.txt";
    private static final String UTF8_ENCODING = "UTF-8";

    private List<String> cache;

    @PostConstruct
    private void init() {
        try {
            Resource resource = new ClassPathResource(WORDS_PATH);
            InputStream is = resource.getInputStream();
            cache = IOUtils.readLines(is, UTF8_ENCODING);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public String getRandomWord() {
        return cache.get(RANDOM.nextInt(cache.size()));
    }
}
