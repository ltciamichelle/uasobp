package com.example.demospringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

 @Entity
 @Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
 @DiscriminatorColumn(name = "person_type")
 @Table(name = "personn")
 public class Person {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @NotNull
 @Size(min = 3, max = 10)
 private String kode;

 @NotNull
 @Size(min = 3, max = 50)
 private String name;

 @Size(min = 11, max = 12)
 private String noHP;

 //SETTER GETTER
 public void setId(Long id) {
 this.id = id;
 }
 public Long getId() {
 return id;
 }
 public void setKode(String kode) {
 this.kode = kode;
 }
 public String getKode() {
 return kode;
 }
 public void setName(String name) {
 this.name = name;
 }
 public String getName() {
 return name;
 }
 public void setNoHP(String noHP) {
 this.noHP = noHP;
 }
 public String getNoHP() {
 return noHP;
 }

//Constructor
public Person() {}

public Person(String kode, String name, String noHP) {
this.kode = kode;
this.name = name;
this. noHP = noHP;
}
 }
