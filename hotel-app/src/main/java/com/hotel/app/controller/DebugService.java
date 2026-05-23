package com.hotel.app.controller;

import com.hotel.app.views.BookingRecord;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class DebugService {

    private final MongoTemplate mongoTemplate;

    public DebugService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void printMongoInfo() {

        // Database name
        String dbName = mongoTemplate.getDb().getName();

        // Collection name for entity
        String collectionName =
                mongoTemplate.getCollectionName(BookingRecord.class);

        System.out.println("DB NAME = " + dbName);
        System.out.println("COLLECTION = " + collectionName);

        // Native MongoDatabase object
        MongoDatabase db = mongoTemplate.getDb();

        System.out.println("DATABASE OBJECT = " + db.getName());
    }
}