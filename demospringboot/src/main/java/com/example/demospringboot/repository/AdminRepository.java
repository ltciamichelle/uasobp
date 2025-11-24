package com.example.demospringboot.repository; // Sesuaikan dengan package Anda

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demospringboot.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    // Method khusus untuk mencari Admin berdasarkan username
    Optional<Admin> findByUsername(String username);
}
