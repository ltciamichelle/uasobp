package com.example.demospringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demospringboot.entity.Buyer;
public interface BuyerRepository extends JpaRepository<Buyer, Long>{}