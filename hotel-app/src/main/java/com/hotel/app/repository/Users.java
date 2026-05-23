package com.hotel.app.repository;

import com.hotel.app.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Users extends MongoRepository<User, Integer> {
    @Query(value = "{ 'username': ?0, 'password': ?1 }",
            fields = "{ '_id': 1 }")
        User getUserId(String username, String password);
}
