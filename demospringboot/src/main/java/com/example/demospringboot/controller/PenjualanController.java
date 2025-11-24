package com.example.demospringboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demospringboot.entity.*;
import com.example.demospringboot.service.*;

@Controller
public class PenjualanController {
  @Autowired
  private PenjualanService penjualanService;
  @Autowired
  private PersonService personService;
  @Autowired
  private ProdukService produkService;

  @GetMapping("/penjualan")
  public String PenjualanPage(Model model) {
    List<Penjualan> penjualanList = penjualanService.getAllPenjualan();
    List<Person> personList = personService.getAllPerson();
    List<Produk> produkList = produkService.getAllProduk();

    model.addAttribute("penjualanList", penjualanList);
    model.addAttribute("personList", personList);
    model.addAttribute("produkList", produkList);
    model.addAttribute("penjualanInfo", new Penjualan());
    return "penjualan.html";
  }

  @PostMapping("/penjualan")
  public String savePenjualan(@ModelAttribute Penjualan penjualan) {
    // Check if ID exists to determine Add or Update
    if (penjualan.getSaleID() != null && penjualan.getSaleID() > 0) {
      penjualanService.updatePenjualan(penjualan.getSaleID(), penjualan);
    } else {
      penjualanService.addPenjualan(penjualan);
    }
    return "redirect:/penjualan";
  }

  @GetMapping("/penjualan/delete")
  public String deletePenjualan(@RequestParam("id") int id) {
    penjualanService.deletePenjualan(id);
    return "redirect:/penjualan";
  }

  @GetMapping("/penjualan/edit")
  public String editPenjualan(@RequestParam("id") int id, Model model) {
    List<Penjualan> penjualanList = penjualanService.getAllPenjualan();
    List<Person> personList = personService.getAllPerson();
    List<Produk> produkList = produkService.getAllProduk();
    Penjualan penjualanRec = penjualanService.getPenjualanById(id);

    model.addAttribute("penjualanList", penjualanList);
    model.addAttribute("personList", personList);
    model.addAttribute("produkList", produkList);
    model.addAttribute("penjualanRec", penjualanRec);

    return "penjualan.html";
  }
}