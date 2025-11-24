package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.model.Kiriman;
import com.example.demospringboot.repository.KirimanRepository;

@Service
public class KirimanService {

    @Autowired
    private KirimanRepository kirimanRepository;

    public List<Kiriman> getAllKiriman() {
        return kirimanRepository.findAll();
    }
}