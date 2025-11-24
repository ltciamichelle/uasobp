package com.example.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demospringboot.model.Kurir;
import com.example.demospringboot.service.KurirService;

@Controller
@RequestMapping("/kurir") // Semua endpoint di controller ini akan diawali /kurir
public class KurirController {

    @Autowired
    private KurirService kurirService;

    // --- 1. ENDPOINT UNTUK MENAMPILKAN DAFTAR KURIR (GET /kurir/manage) ---
    @GetMapping("/manage")
    public String manageKurir(Model model) {
        model.addAttribute("kurirList", kurirService.getAllKurir());
        // Objek kosong untuk form tambah baru
        model.addAttribute("newKurir", new Kurir()); 
        return "admin_kurir_manage"; 
    }

    // --- 2. ENDPOINT UNTUK MEMPROSES TAMBAH/EDIT KURIR (POST /kurir/save) ---
    @PostMapping("/save")
    public String saveKurir(@ModelAttribute Kurir kurir) {
        kurirService.saveKurir(kurir);
        return "redirect:/kurir/manage";
    }

    // --- 3. ENDPOINT UNTUK MENGHAPUS KURIR (GET /kurir/delete/{id}) ---
    @GetMapping("/delete/{id}")
    public String deleteKurir(@PathVariable Long id) {
        kurirService.deleteKurir(id);
        return "redirect:/kurir/manage";
    }

    // --- 4. ENDPOINT UNTUK MENGISI DATA KE FORM EDIT (GET /kurir/edit/{id}) ---
    @GetMapping("/edit/{id}")
    public String editKurir(@PathVariable Long id, Model model) {
        // Ambil data Kurir lama
        Kurir existingKurir = kurirService.getKurirById(id);
        
        // Muat daftar kurir dan data yang akan diedit
        model.addAttribute("kurirList", kurirService.getAllKurir());
        model.addAttribute("newKurir", existingKurir); // Isi form dengan data lama
        
        return "admin_kurir_manage"; 
    }
}