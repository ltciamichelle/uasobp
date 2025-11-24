package com.example.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringboot.entity.Produk;
import com.example.demospringboot.service.ProdukService;

import jakarta.validation.Valid;

@Controller
public class ProdukController {
    @Autowired
    private ProdukService produkService;

    @GetMapping("/produk")
    public String ProdukPage(Model model) {
        List<Produk> produkList = produkService.getAllProduk();
        model.addAttribute("produkList", produkList);
        model.addAttribute("produkRec", new Produk()); // Always provide produkRec for form binding
        return "produk.html";
    }

    @PostMapping("/produk")
    public String saveProduk(@Valid @ModelAttribute("produkRec") Produk produk, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            List<Produk> produkList = produkService.getAllProduk();
            model.addAttribute("produkList", produkList);
            return "produk.html";
        }

        // Check if ID exists to determine Add or Update
        if (produk.getId() != null && produk.getId() > 0) {
            produkService.updateProduk(produk.getId(), produk);
        } else {
            produkService.addProduk(produk);
        }
        return "redirect:/produk";
    }

    @GetMapping("/produk/delete")
    public String deleteProduk(@RequestParam("id") long id) {
        produkService.deleteProduk(id);
        return "redirect:/produk";
    }

    @GetMapping("/produk/edit")
    public String editProduk(@RequestParam("id") long id, Model model) {
        List<Produk> produkList = produkService.getAllProduk();
        Produk produkRec = produkService.getProdukById(id);

        model.addAttribute("produkList", produkList);
        model.addAttribute("produkRec", produkRec);

        return "produk.html";
    }
}