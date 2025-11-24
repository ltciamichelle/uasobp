package com.example.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demospringboot.model.Admin;
import com.example.demospringboot.model.KirimanReguler; // WAJIB untuk inisialisasi form kosong
import com.example.demospringboot.model.LoginRequest;
import com.example.demospringboot.service.AdminService;
import com.example.demospringboot.service.KirimanService; // WAJIB: Import KirimanService

import jakarta.servlet.http.HttpServletRequest;

@Controller 
@RequestMapping("/admin") 
public class AdminMvcController {

    @Autowired
    private AdminService adminService;
    
    // KEKURANGAN YANG HILANG: Inject KirimanService
    @Autowired
    private KirimanService kirimanService;

    // --- 1. ENDPOINT LOGIN (GET) ---
    @GetMapping("/login")
    public String showAdminLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest()); 
        return "admin_login"; 
    }
    
    // --- 2. ENDPOINT PROSES LOGIN (POST) ---
    @PostMapping("/login")
    public String loginAdmin(@ModelAttribute LoginRequest loginRequest, 
                             HttpServletRequest request) {
        
        Admin admin = adminService.loginAdmin(
            loginRequest.getUsername(), 
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
        // 1. Cek sesi: Jika Admin tidak ada di sesi, arahkan ke login
        if (request.getSession().getAttribute("Admin") == null) {
            return "redirect:/admin/login"; 
        }
        
        // 2. KOREKSI: Ambil data Kiriman dari Service
        model.addAttribute("kirimanList", kirimanService.getAllKiriman());
        
        // 3. Tambahkan objek kosong untuk Form Input Kiriman
        model.addAttribute("kirimanBaru", new KirimanReguler()); 

        return "admin_dashboard";
    }
}