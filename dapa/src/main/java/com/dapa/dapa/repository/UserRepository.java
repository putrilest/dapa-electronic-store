package com.dapa.dapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dapa.dapa.entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
