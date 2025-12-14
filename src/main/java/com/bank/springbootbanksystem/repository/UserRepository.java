package com.bank.springbootbanksystem.repository;

import com.bank.springbootbanksystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
}
