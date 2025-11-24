package com.example.demospringboot.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

 @Entity
 @DiscriminatorValue("BUYER")
 public class Buyer extends Person{
 private String alamat;
 public Buyer() {}

 public Buyer(String kode, String name, 
String noHP, String alamat) {
 super(kode, name, noHP);
 this.alamat = alamat;
 }

 public void setAlamat(String alamat) {
 this.alamat = alamat;
 }

 public String getAlamat() {
 return alamat;
 }
 }
