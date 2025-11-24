package com.example.demospringboot.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.model.Kiriman;
import com.example.demospringboot.model.KirimanKargo;
import com.example.demospringboot.model.KirimanKilat; 
import com.example.demospringboot.model.KirimanReguler;
import com.example.demospringboot.model.Kurir;
import com.example.demospringboot.repository.KirimanRepository;

@Service
public class EkspedisiService {
    
    @Autowired 
    private KirimanRepository kirimanRepository;

    private static final String HISTORY_FILE = "histori.txt";
    
    // Pricing Constants
    private static final double BIAYA_REGULER_PER_KG = 8000;
    private static final double BIAYA_KILAT_PER_KG = 5000;
    private static final double BIAYA_DASAR_KILAT = 20000;
    private static final double BIAYA_KARGO_PER_KG = 10000;

    // KOREKSI UTAMA: Tambahkan alamatPengirim ke signature
    public Kiriman prosesKiriman(String tipe, String resi, String alamatPengirim, String tujuan, double berat, double volume, Long id, Kurir kurir) {
        
        // KOREKSI: Tambahkan validasi alamat
        if (alamatPengirim == null || alamatPengirim.trim().isEmpty()) {
             throw new IllegalArgumentException("Alamat Pengirim wajib diisi.");
        }
        
        Kiriman kiriman = null;

        switch (tipe.toLowerCase()) {
            case "reguler":
                // KOREKSI: Kirim alamatPengirim ke constructor
                kiriman = new KirimanReguler(resi, alamatPengirim, tujuan, berat, BIAYA_REGULER_PER_KG);
                break;
            case "kilat":
                // KOREKSI: Kirim alamatPengirim ke constructor
                kiriman = new KirimanKilat(resi, alamatPengirim, tujuan, berat, BIAYA_KILAT_PER_KG, BIAYA_DASAR_KILAT);
                break;
            case "kargo":
                if (berat < 10) {
                    throw new IllegalArgumentException("Berat minimal untuk Kargo adalah 10kg");
                }
                // KOREKSI: Kirim alamatPengirim ke constructor
                kiriman = new KirimanKargo(resi, alamatPengirim, tujuan, berat, BIAYA_KARGO_PER_KG, volume);
                break;
            default:
                throw new IllegalArgumentException("Tipe kiriman tidak valid");
        }
        
        // Logika Update (JPA)
        if (id != null && id > 0) {
            Optional<Kiriman> existingKiriman = kirimanRepository.findById(id);
            if (existingKiriman.isPresent()) {
                kiriman.setId(id);
            } 
        }
        
        // Set objek Kurir
        if (kurir != null) {
            kiriman.setKurir(kurir); 
        }

        Kiriman savedKiriman = kirimanRepository.save(kiriman);
        
        saveToHistory(savedKiriman);
        
        return savedKiriman; 
    }

    public void deleteKiriman(Long id) {
        kirimanRepository.deleteById(id);
    }

    public Kiriman getKirimanById(Long id) {
        return kirimanRepository.findById(id).orElse(null);
    }

    public List<Kiriman> getDaftarKiriman() {
        return kirimanRepository.findAll();
    }
    
    // Method saveToHistory
    private void saveToHistory(Kiriman kiriman) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            String log = String.format("[%s] Resi: %s, Tipe: %s, Tujuan: %s, Berat: %.2f kg, Biaya: %.2f%n",
                    LocalDateTime.now(),
                    kiriman.getNomorResi(),
                    kiriman.getClass().getSimpleName(),
                    kiriman.getAlamatTujuan(),
                    kiriman.getBeratBarang(),
                    kiriman.hitungBiaya());
            writer.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}