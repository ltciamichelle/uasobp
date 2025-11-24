package com.example.demospringboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Seller;
import com.example.demospringboot.repository.SellerRepository;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getAllSeller() {
        return sellerRepository.findAll();
    }

    public Seller findSeller(String kode) {
    return sellerRepository.findByKode(kode);
    }

    public Seller addSeller(Seller obj) {
        // Mengikuti logika addBuyer Anda (tanpa setId(null), ini sudah benar)
        return sellerRepository.save(obj);
    }

    public Seller getSellerById(long id) {
        return sellerRepository.findById(id).orElse(null);
    }

    // Method tambahan berdasarkan SellerRepository Anda
    public Seller getSellerByKode(String kode) {
        return sellerRepository.findByKode(kode);
    }

    public Seller updateSeller(long id, Seller obj) {
        obj.setId(id);
        return sellerRepository.save(obj);
    }

    public void deleteSeller(long id) {
        sellerRepository.deleteById(id);
    }
}