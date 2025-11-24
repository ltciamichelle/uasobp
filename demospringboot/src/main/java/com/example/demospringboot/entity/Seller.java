package com.example.demospringboot.entity;

import jakarta.persistence.Entity; 
import jakarta.validation.constraints.Size;
import jakarta.persistence.DiscriminatorValue;

 @Entity
 @DiscriminatorValue("SELLER")
 public class Seller extends Person{
 @Size(min = 8)
 private String password;
 public Seller() {}

public Seller(String kode, String name, 
String noHP, String password) {
 super(kode, name, noHP);
 this.password = password;
 }
 public void setPassword(String password) {
 this.password = password;
 }
 public String getPassword() {
 return password;
 }
}