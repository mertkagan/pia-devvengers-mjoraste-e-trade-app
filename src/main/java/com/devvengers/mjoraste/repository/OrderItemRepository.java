package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
