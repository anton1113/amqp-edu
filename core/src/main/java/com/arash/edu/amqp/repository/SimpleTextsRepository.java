package com.arash.edu.amqp.repository;

import com.arash.edu.amqp.msg.db.SimpleTexts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleTextsRepository extends MongoRepository<SimpleTexts, String> {
}
