package com.example.demospringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Reguler") // Nilai yang disimpan di kolom 'tipe_kiriman'
public class KirimanReguler extends Kiriman {
    
    private double biayaPerKg;

    // 1. WAJIB: Konstruktor tanpa argumen untuk JPA
    public KirimanReguler() {
        super();
    }
    
    // KOREKSI UTAMA: Tambahkan alamatPengirim ke signature constructor
    public KirimanReguler(String nomorResi, String alamatPengirim, String alamatTujuan, double beratBarang, double biayaPerKg) {
        // Memanggil konstruktor Kiriman dengan 4 data utama
        super(nomorResi, alamatPengirim, alamatTujuan, beratBarang); 
        this.biayaPerKg = biayaPerKg;
    }

    @Override
    public double hitungBiaya() {
        return beratBarang * biayaPerKg;
    }

    public double getBiayaPerKg() {
        return biayaPerKg;
    }

    public void setBiayaPerKg(double biayaPerKg) {
        this.biayaPerKg = biayaPerKg;
    }
}
