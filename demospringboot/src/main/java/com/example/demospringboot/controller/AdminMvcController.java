package com.example.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demospringboot.model.Admin;
import com.example.demospringboot.model.KirimanReguler; 
import com.example.demospringboot.model.LoginRequest;
import com.example.demospringboot.service.AdminService;
import com.example.demospringboot.service.KirimanService;
import com.example.demospringboot.service.KurirService; // ⬅ PERBAIKAN: Import KurirService

import jakarta.servlet.http.HttpServletRequest;

@Controller 
@RequestMapping("/admin") 
public class AdminMvcController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private KirimanService kirimanService;

    // ⬅ PERBAIKAN: Suntikkan (Autowired) KurirService
    @Autowired
    private KurirService kurirService;
    
    // --- 1. ENDPOINT LOGIN (GET) ---
    @GetMapping("/login")
// ... (Bagian ini tidak berubah) ...
    public String showAdminLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest()); 
        return "admin_login"; 
    }
    
    // --- 2. ENDPOINT PROSES LOGIN (POST) ---
    @PostMapping("/login")
// ... (Bagian ini tidak berubah) ...
    public String loginAdmin(@ModelAttribute LoginRequest loginRequest, 
                             HttpServletRequest request) {
        
        Admin admin = adminService.loginAdmin(
            loginRequest.getUsername(), 
// ... (Bagian ini tidak berubah) ...
            loginRequest.getPassword()
        );

        if (admin != null) {
            // KOREKSI: Set Sesi Admin
            request.getSession().setAttribute("Admin", admin); 
            return "redirect:/admin/dashboard"; 
        } else {
            return "redirect:/admin/login?error"; 
        }
    }
    
    // --- 3. ENDPOINT DASHBOARD (GET) ---
    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpServletRequest request) {
        // Cek sesi: Jika Admin tidak ada di sesi, arahkan ke login
        if (request.getSession().getAttribute("Admin") == null) {
            return "redirect:/admin/login"; 
        }
        
        // Ambil data Kiriman dari Service
        model.addAttribute("kirimanList", kirimanService.getAllKiriman());
        
        // Tambahkan objek kosong untuk Form Input Kiriman
        model.addAttribute("kirimanBaru", new KirimanReguler()); 
        
        // ⬅ PERBAIKAN: Ambil daftar kurir dan tambahkan ke Model
        model.addAttribute("kurirList", kurirService.getAllKurir());
        
        return "admin_dashboard";
    }
    
    // --- 4. ENDPOINT LOGOUT (KOREKSI: DITAMBAHKAN) ---
// ... (Bagian ini tidak berubah) ...
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Membersihkan sesi saat ini
        request.getSession().invalidate(); 
        
        // Mengarahkan kembali ke halaman login Admin
        return "redirect:/admin/login"; 
    }
}