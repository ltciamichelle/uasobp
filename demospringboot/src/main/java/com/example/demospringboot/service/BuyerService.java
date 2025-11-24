package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Buyer;
import com.example.demospringboot.repository.BuyerRepository;

@Service
public class BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;

    public List<Buyer> getAllBuyer() {
        return buyerRepository.findAll();
    }
    public Buyer addBuyer(Buyer obj) {
    Long id = null;
    obj.setId(id);
    return buyerRepository.save(obj);
}

    public Buyer getBuyerById(long id) {
        return buyerRepository.findById(id).orElse(null);
    }

    public Buyer updateBuyer(long id, Buyer obj) {
        obj.setId(id); 
        return buyerRepository.save(obj);
    }

    public void deleteBuyer(long id) {
        buyerRepository.deleteById(id);
    }
}