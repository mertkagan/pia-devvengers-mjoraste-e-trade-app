package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    void deleteByUserId(Long userId);
}
