package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
