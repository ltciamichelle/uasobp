package com.example.demospringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Kargo") // Nilai yang disimpan di kolom 'tipe_kiriman'
public class KirimanKargo extends Kiriman {
    
    private double biayaPerKg;
    private double volumeBarang; // in m3 or arbitrary unit

    // 1. WAJIB: Konstruktor tanpa argumen untuk JPA
    public KirimanKargo() {
        super();
    }
    
    // KOREKSI UTAMA: Tambahkan alamatPengirim ke signature constructor
    public KirimanKargo(String nomorResi, String alamatPengirim, String alamatTujuan, double beratBarang, double biayaPerKg,
            double volumeBarang) {
        // Memanggil constructor Kiriman dengan 4 data utama
        super(nomorResi, alamatPengirim, alamatTujuan, beratBarang);
        this.biayaPerKg = biayaPerKg;
        this.volumeBarang = volumeBarang;
    }

    @Override
    public double hitungBiaya() {
        // Perhitungan ini tetap menggunakan logika Anda (berat asli * biaya per kg)
        return beratBarang * biayaPerKg;
    }

    // --- Getters and Setters ---
    
    public double getBiayaPerKg() {
        return biayaPerKg;
    }

    public void setBiayaPerKg(double biayaPerKg) {
        this.biayaPerKg = biayaPerKg;
    }

    public double getVolumeBarang() {
        return volumeBarang;
    }

    public void setVolumeBarang(double volumeBarang) {
        this.volumeBarang = volumeBarang;
    }
}