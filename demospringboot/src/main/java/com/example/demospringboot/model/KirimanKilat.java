package com.example.demospringboot.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Kilat") // Nilai yang disimpan di kolom 'tipe_kiriman'
public class KirimanKilat extends Kiriman {
    
    private double biayaPerKg;
    private double biayaDasarKilat;

    // 1. WAJIB: Konstruktor tanpa argumen untuk JPA
    public KirimanKilat() {
        super();
    }

    // KOREKSI UTAMA: Tambahkan alamatPengirim ke signature constructor
    public KirimanKilat(String nomorResi, String alamatPengirim, String alamatTujuan, double beratBarang, double biayaPerKg,
            double biayaDasarKilat) {
        // Memanggil constructor Kiriman dengan 4 data utama
        super(nomorResi, alamatPengirim, alamatTujuan, beratBarang);
        this.biayaPerKg = biayaPerKg;
        this.biayaDasarKilat = biayaDasarKilat;
    }

    @Override
    public double hitungBiaya() {
        // Biaya = (Berat * Biaya per Kg) + Biaya Dasar
        return (beratBarang * biayaPerKg) + biayaDasarKilat;
    }

    // --- Getters and Setters ---
    
    public double getBiayaPerKg() {
        return biayaPerKg;
    }

    public void setBiayaPerKg(double biayaPerKg) {
        this.biayaPerKg = biayaPerKg;
    }

    public double getBiayaDasarKilat() {
        return biayaDasarKilat;
    }

    public void setBiayaDasarKilat(double biayaDasarKilat) {
        this.biayaDasarKilat = biayaDasarKilat;
    }
}