package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BrandRepository extends JpaRepository<Brand,Long> {


}
