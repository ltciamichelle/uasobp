package com.example.demospringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demospringboot.model.Kurir;

@Repository
public interface KurirRepository extends JpaRepository<Kurir, Long> {
    
    // Spring Data JPA secara otomatis menyediakan:
    // save(), findAll(), findById(), deleteById(), dll.
    
    /**
     * Method kustom untuk mencari Kurir berdasarkan ID Kurir sistem (idKurir).
     * Contoh: findByIdKurir("K-001")
     */
    Optional<Kurir> findByIdKurir(String idKurir);
}
