package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
