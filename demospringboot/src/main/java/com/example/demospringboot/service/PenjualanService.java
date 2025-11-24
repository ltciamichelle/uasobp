package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Penjualan;
import com.example.demospringboot.repository.PenjualanRepository;
import java.util.List;

@Service
public class PenjualanService {
  @Autowired
  private PenjualanRepository penjualanRepository;

  public List<Penjualan> getAllPenjualan() {
    return penjualanRepository.findAll();
  }

  public Penjualan addPenjualan(Penjualan obj) {
    Long id = null;
    obj.setSaleID(id);
    return penjualanRepository.save(obj);
  }

  public Penjualan getPenjualanById(long id) {
    return penjualanRepository.findById(id).orElse(null);
  }

  public Penjualan updatePenjualan(long id, Penjualan obj) {
    obj.setSaleID(id);
    return penjualanRepository.save(obj);
  }

  public void deletePenjualan(long id) {
    penjualanRepository.deleteById(id);
  }
}