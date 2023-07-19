package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    Optional<User> findOneByEmailAndPassword(String email, String encodedPassword);

    Optional<User> findById(Long userId);

}
