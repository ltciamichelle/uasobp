package com.example.demospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demospringboot.model.Kiriman;

@Repository
public interface KirimanRepository extends JpaRepository<Kiriman, Long> {
    
    /**
     * Metode kustom untuk mencari kiriman berdasarkan nomor resi.
     * Berguna untuk pengecekan duplikasi atau pencarian detail paket.
     */
    Optional<Kiriman> findByNomorResi(String nomorResi);
    
    /**
     * Metode kustom untuk mencari kiriman berdasarkan status (misal: "Diproses", "Dikirim", "Tiba").
     */
    List<Kiriman> findByStatus(String status);
}