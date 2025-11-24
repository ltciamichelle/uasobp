package com.example.demospringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demospringboot.entity.Penjualan;
public interface PenjualanRepository extends JpaRepository<Penjualan, Long>{}