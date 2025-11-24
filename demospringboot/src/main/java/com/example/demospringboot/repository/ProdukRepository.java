package com.example.demospringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demospringboot.entity.Produk;
public interface ProdukRepository extends JpaRepository<Produk, Long> {}