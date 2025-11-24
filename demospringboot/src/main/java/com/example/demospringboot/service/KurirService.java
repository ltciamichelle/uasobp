package com.example.demospringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Diperlukan untuk findById
import java.util.Random; // Diperlukan untuk ID generator sederhana

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // WAJIB untuk operasi update JPA

import com.example.demospringboot.model.Kiriman;
import com.example.demospringboot.model.Kurir;
import com.example.demospringboot.repository.KirimanRepository; // Tambahkan ini
import com.example.demospringboot.repository.KurirRepository;

@Service
public class KurirService {

    @Autowired
    private KurirRepository kurirRepository;
    
    @Autowired // Inject KirimanRepository juga jika Anda berencana menggunakannya di Service ini
    private KirimanRepository kirimanRepository; 

    public List<Kurir> getAllKurir() {
        return kurirRepository.findAll();
    }

    // KOREKSI UTAMA: Modifikasi saveKurir untuk auto-generate idKurir_sistem
    @Transactional // Penting untuk operasi database
    public Kurir saveKurir(Kurir kurir) {
        
        // Cek apakah ini operasi INSERT (ID JPA null) DAN field idKurir belum diset
        if (kurir.getId() == null && (kurir.getIdKurir() == null || kurir.getIdKurir().trim().isEmpty())) {
            
            // Logika untuk menghasilkan ID Kurir Sistem yang unik
            String newId = generateUniqueKurirId();
            kurir.setIdKurir(newId);
        }
        
        // Pastikan Status default terpasang jika kosong
        if (kurir.getStatus() == null || kurir.getStatus().isEmpty()) {
            kurir.setStatus("Aktif");
        }

        return kurirRepository.save(kurir);
    }

    public Kurir getKurirById(Long id) {
        return kurirRepository.findById(id).orElse(null);
    }

    public void deleteKurir(Long id) {
        kurirRepository.deleteById(id);
    }
    
    // METHOD BARU (POIN 2A): Update Status dan Penugasan (Jika diperlukan)
    @Transactional
    public boolean updateStatusAndAssign(Long kirimanId, String newStatus, Long newKurirId) {
        Optional<Kiriman> kirimanOpt = kirimanRepository.findById(kirimanId);
        Optional<Kurir> kurirOpt = kurirRepository.findById(newKurirId);
        
        if (kirimanOpt.isPresent() && kurirOpt.isPresent()) {
            Kiriman kiriman = kirimanOpt.get();
            Kurir kurir = kurirOpt.get();
            
            kiriman.setStatus(newStatus);
            kiriman.setKurir(kurir);
            
            kirimanRepository.save(kiriman);
            return true;
        }
        return false;
    }
    
    // METHOD TAMBAHAN UNTUK LAPORAN KINERJA (POIN 2B)
    public List<Object[]> getKurirPerformanceSummary() {
        // Implementasi dummy untuk UI
        return new ArrayList<>(); 
    }
    
    /**
     * Helper method untuk generate ID Kurir yang unik (sederhana)
     */
    private String generateUniqueKurirId() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000); 
        return String.format("KR-%05d", randomNumber);
    }
}