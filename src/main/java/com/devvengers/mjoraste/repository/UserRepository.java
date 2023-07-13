package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    Optional<User> findOneByEmailAndPassword(String email, String encodedPassword);
}
