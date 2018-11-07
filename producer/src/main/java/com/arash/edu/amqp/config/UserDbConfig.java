package com.arash.edu.amqp.config;

import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * Created by anton on 25.07.18.
 *
 */
@Configuration
@EnableMongoRepositories(
        basePackages = "com.arash.edu.amqp.repository",
        mongoTemplateRef = "userMongoTemplate"
)
public class UserDbConfig {

    @Value("${mongo.db.user.uri}")
    private String uri;

    @Bean
    public MongoDbFactory userMongoDbFactory() throws UnknownHostException {
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        return new SimpleMongoDbFactory(mongoClientURI);
    }

    @Bean
    public MongoTemplate userMongoTemplate() throws Exception {
        MongoTemplate userMongoTemplate = new MongoTemplate(userMongoDbFactory());
        userMongoTemplate.setWriteConcern(WriteConcern.MAJORITY);
        return userMongoTemplate;
    }
}
