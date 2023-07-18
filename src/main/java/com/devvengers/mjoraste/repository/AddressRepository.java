package com.devvengers.mjoraste.repository;

import com.devvengers.mjoraste.entities.Address;
import com.devvengers.mjoraste.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {

    Optional<Address> findByUserId(Long userId);
}
