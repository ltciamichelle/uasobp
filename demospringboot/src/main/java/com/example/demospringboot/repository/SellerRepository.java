package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
    Seller findByKode(String kode);
}
