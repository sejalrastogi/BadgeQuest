package com.coderhack.coderHack.repository;

import com.coderhack.coderHack.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
}
