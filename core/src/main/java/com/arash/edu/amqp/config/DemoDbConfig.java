package com.arash.edu.amqp.config;

import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.arash.edu.amqp.repository",
        mongoTemplateRef = "demoMongoTemplate"
)
public class DemoDbConfig {

    private static final String MONGO_DB_URI = "mongodb://localhost:27017/demo";

    @Bean
    public MongoDbFactory demoMongoDbFactory() throws UnknownHostException {
        MongoClientURI mongoClientURI = new MongoClientURI(MONGO_DB_URI);
        return new SimpleMongoDbFactory(mongoClientURI);
    }

    @Bean
    public MongoTemplate demoMongoTemplate() throws Exception {
        MongoTemplate demoMongoTemplate = new MongoTemplate(demoMongoDbFactory());
        demoMongoTemplate.setWriteConcern(WriteConcern.MAJORITY);
        return demoMongoTemplate;
    }
}

