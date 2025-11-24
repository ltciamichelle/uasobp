// File: LogoutController.java (Contoh Controller baru)

package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller // Tidak ada @RequestMapping di sini
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 1. Membersihkan Sesi (Sesi Admin)
        request.getSession().invalidate(); 
        
        // 2. Mengarahkan ke halaman login Admin
        return "redirect:/admin/login"; 
    }
}