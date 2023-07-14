package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
