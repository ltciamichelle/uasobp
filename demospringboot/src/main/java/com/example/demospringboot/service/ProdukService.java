package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Produk;
import com.example.demospringboot.repository.ProdukRepository;

@Service
public class ProdukService {
    @Autowired
    private ProdukRepository produkRepository;

    public List<Produk> getAllProduk() {
        return produkRepository.findAll();
    }

    public Produk addProduk(Produk produk) {
        return produkRepository.save(produk);
    }

    public Produk updateProduk(long id, Produk produk) {
        produk.setId(id);
        return produkRepository.save(produk);
    }

    public void deleteProduk(long id) {
        produkRepository.deleteById(id);
    }

    public Produk getProdukById(long id) {
        return produkRepository.findById(id).orElse(null);
    }
}
