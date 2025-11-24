package com.example.demospringboot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "kurir")
public class Kurir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT AUTO_INCREMENT")
    private Long id;

    @Column(name = "id_kurir_sistem", unique = true, nullable = false, length = 10)
    private String idKurir;

    @Column(name = "nama", nullable = false, length = 100)
    private String nama;

    @Column(name = "nomor_telepon", length = 15)
    private String nomorTelepon;

    @Column(name = "status", length = 20)
    private String status = "Aktif";

    @OneToMany(mappedBy = "kurir", fetch = FetchType.LAZY)
    private List<Kiriman> daftarKiriman = new ArrayList<>();

    public Kurir() {
    }

    public Kurir(String idKurir, String nama, String nomorTelepon) {
        this.idKurir = idKurir;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.daftarKiriman = new ArrayList<>();
    }

    public void tambahKiriman(Kiriman kiriman) {
        this.daftarKiriman.add(kiriman);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Kiriman> getDaftarKiriman() {
        return daftarKiriman;
    }

    public void setDaftarKiriman(List<Kiriman> daftarKiriman) {
        this.daftarKiriman = daftarKiriman;
    }
}