package com.example.demo.UserRepository;

import com.example.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
