package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.About;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AboutRepository extends MongoRepository<About, String> {



}
