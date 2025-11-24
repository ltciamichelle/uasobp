package com.example.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringboot.model.Kiriman;
import com.example.demospringboot.model.KirimanReguler;
import com.example.demospringboot.model.Kurir;
import com.example.demospringboot.service.EkspedisiService;
import com.example.demospringboot.service.KurirService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EkspedisiController {

    @Autowired
    private EkspedisiService ekspedisiService;
    
    @Autowired 
    private KurirService kurirService; 

    // --- Method untuk memeriksa izin Admin ---
    private boolean isAdminLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("Admin") != null;
    }

    // --- 1. ENDPOINT UNTUK MEMPROSES INPUT/UPDATE KIRIMAN (POST) ---
    @PostMapping("/kiriman")
    public String prosesKirimanBaru(
            @RequestParam String tipe,
            @RequestParam String resi,
            @RequestParam String alamatPengirim, // KOREKSI 1: Tangkap Alamat Pengirim
            @RequestParam String tujuan,
            @RequestParam double berat,
            @RequestParam(required = false, defaultValue = "0.0") double volume,
            @RequestParam(required = false) Long id, 
            @RequestParam(required = false) Long kurirId, 
            Model model, HttpServletRequest request) {

        if (!isAdminLoggedIn(request)) {
            return "redirect:/admin/login";
        }
        
        Kurir kurir = null;
        if (kurirId != null && kurirId > 0) {
             kurir = kurirService.getKurirById(kurirId);
        }

        try {
            // KOREKSI 2: Panggil service dengan urutan parameter yang baru
            ekspedisiService.prosesKiriman(tipe, resi, alamatPengirim, tujuan, berat, volume, id, kurir);
            
            return "redirect:/admin/dashboard"; 

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            
            // Muat ulang data yang dibutuhkan dashboard jika gagal
            model.addAttribute("kirimanList", ekspedisiService.getDaftarKiriman()); 
            model.addAttribute("kurirList", kurirService.getAllKurir());
            model.addAttribute("kirimanBaru", new KirimanReguler());
            
            return "admin_dashboard";
        }
    }

    // --- 2. ENDPOINT UNTUK HAPUS KIRIMAN ---
    @GetMapping("/kiriman/delete")
    public String deleteKiriman(@RequestParam Long id, HttpServletRequest request) {
        if (!isAdminLoggedIn(request)) {
            return "redirect:/admin/login";
        }
        ekspedisiService.deleteKiriman(id);
        return "redirect:/admin/dashboard"; 
    }

    // --- 3. ENDPOINT UNTUK EDIT KIRIMAN ---
    @GetMapping("/kiriman/edit")
    public String editKiriman(@RequestParam Long id, Model model, HttpServletRequest request) {
        if (!isAdminLoggedIn(request)) {
            return "redirect:/admin/login";
        }
        
        Kiriman k = ekspedisiService.getKirimanById(id);
        
        model.addAttribute("kirimanEdit", k);
        
        model.addAttribute("kurirList", kurirService.getAllKurir());
        model.addAttribute("kirimanList", ekspedisiService.getDaftarKiriman());
        
        return "admin_dashboard"; 
    }
}