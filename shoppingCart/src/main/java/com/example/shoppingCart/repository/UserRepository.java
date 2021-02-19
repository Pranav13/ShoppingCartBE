package com.example.shoppingCart.repository;

import com.example.shoppingCart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<Boolean> existsByUsername(String username);

    Optional<Boolean> existsByEmail(String email);
}
