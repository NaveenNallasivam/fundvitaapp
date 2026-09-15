package com.example.fundvita.repository;

import com.example.fundvita.entity.User;
import com.example.fundvita.entity.impl.UserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserImpl, Long> {
    Optional<UserImpl> findByEmail(String email);
}
