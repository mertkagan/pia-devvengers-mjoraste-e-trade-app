package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.entities.Cart;
import com.devvengers.mjoraste.entities.User;
import com.devvengers.mjoraste.service.responses.GetUserCartResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    void deleteByUserId(Long userId);



}
