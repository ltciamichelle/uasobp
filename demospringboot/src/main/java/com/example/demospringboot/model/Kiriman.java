package com.example.demospringboot.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "kiriman")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipe_kiriman", discriminatorType = DiscriminatorType.STRING)
public abstract class Kiriman implements PerhitunganBiaya {
    
    // --- FIELDS UTAMA ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "nomor_resi", unique = true, nullable = false)
    protected String nomorResi;
    
    // >>> FIELD BARU: ALAMAT PENGIRIM <<<
    @Column(name = "alamat_pengirim", nullable = false) 
    protected String alamatPengirim;
    
    // FIELD LAMA: Menjadi Alamat Penerima (Tujuan)
    @Column(name = "alamat_tujuan", nullable = false) 
    protected String alamatTujuan;
    
    @Column(name = "berat_barang", nullable = false)
    protected double beratBarang;
    
    @Column(name = "status", nullable = false)
    protected String status;
    
    @Column(name = "tanggal_kirim", nullable = false)
    protected LocalDateTime tanggalKirim;
    
    // --- FIELD RELASI KURIR ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kurir_id") 
    protected Kurir kurir;
    
    // --- CONSTRUCTORS ---
    
    // 1. Constructor Wajib tanpa argumen untuk JPA
    public Kiriman() {}

    // 2. KOREKSI: Constructor berargumen harus menerima alamat pengirim dan tujuan
    public Kiriman(String nomorResi, String alamatPengirim, String alamatTujuan, double beratBarang) {
        this.nomorResi = nomorResi;
        this.alamatPengirim = alamatPengirim;
        this.alamatTujuan = alamatTujuan;
        this.beratBarang = beratBarang;
        this.status = "Diproses";
        this.tanggalKirim = LocalDateTime.now(); 
    }

    // --- GETTERS AND SETTERS ---

    // ID
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }
    
    // Status
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }
    
    // Nomor Resi
    public String getNomorResi() { return nomorResi; }
    public void setNomorResi(String nomorResi) { this.nomorResi = nomorResi; }
    
    // >>> GETTER & SETTER BARU: ALAMAT PENGIRIM <<<
    public String getAlamatPengirim() { return alamatPengirim; }
    public void setAlamatPengirim(String alamatPengirim) { this.alamatPengirim = alamatPengirim; }

    // Alamat Tujuan (Penerima)
    public String getAlamatTujuan() { return alamatTujuan; }
    public void setAlamatTujuan(String alamatTujuan) { this.alamatTujuan = alamatTujuan; }
    
    // Berat Barang
    public double getBeratBarang() { return beratBarang; }
    public void setBeratBarang(double beratBarang) { this.beratBarang = beratBarang; }

    // Tanggal Kirim
    public LocalDateTime getTanggalKirim() { return tanggalKirim; }
    public void setTanggalKirim(LocalDateTime tanggalKirim) { this.tanggalKirim = tanggalKirim; }
    
    // GETTER & SETTER UNTUK KURIR
    public Kurir getKurir() { return kurir; }
    public void setKurir(Kurir kurir) { this.kurir = kurir; }

    // Method Tipe Kiriman (digunakan di dashboard)
    public String getTipeKiriman() {
        return this.getClass().getSimpleName();
    }
    public void setTipeKiriman(String tipeKiriman) {
        // Hanya untuk JPA, biarkan kosong
    }
    
    // Implementasi abstract method hitungBiaya() ada di subclass
}