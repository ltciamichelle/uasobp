package com.example.demospringboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringboot.entity.Buyer;
import com.example.demospringboot.entity.Person;
import com.example.demospringboot.entity.Seller;
import com.example.demospringboot.service.BuyerService;
import com.example.demospringboot.service.PersonService;
import com.example.demospringboot.service.SellerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @GetMapping(value = { "/person", "/person/" })
    public String PersonPage(Model model) {
        List<Person> personList = personService.getAllPerson();
        model.addAttribute("personList", personList);
        model.addAttribute("personInfo", new Person());
        return "person.html";
    }

    @GetMapping("/person/{id}")
    public String personGetRec(Model model, @PathVariable("id") long id) {
        List<Person> personList = personService.getAllPerson();
        Person personRec = personService.getPersonById(id);
        model.addAttribute("personList", personList);
        model.addAttribute("personRec", personRec);
        return "person.html";
    }

    @PostMapping(value = { "/person/submit/", "/person/submit/{id}" }, params = { "add" })
    public String personAdd(@ModelAttribute("personInfo") Person personInfo) {
        personService.addPerson(personInfo);
        return "redirect:/person";
    }

    @PostMapping(value = "/person/submit/{id}", params = { "edit" })
    public String personEdit(@ModelAttribute("personInfo") Person personInfo,
            @PathVariable("id") long id) {
        personService.updatePerson(id, personInfo);
        return "redirect:/person";
    }

    @PostMapping(value = "/person/submit/{id}", params = { "delete" })
    public String personDelete(@PathVariable("id") long id) {
        personService.deletePerson(id);
        return "redirect:/person";
    }

    // ======================================
    // BUYER
    // ======================================

    @GetMapping(value = { "/buyer", "/buyer/" })
    public String BuyerPage(Model model) {
        List<Buyer> buyerList = buyerService.getAllBuyer();
        model.addAttribute("buyerList", buyerList);
        model.addAttribute("buyerInfo", new Buyer());
        return "buyer.html";
    }

    @GetMapping("/buyer/{id}")
    public String buyerGetRec(Model model, @PathVariable("id") long id) {
        List<Buyer> buyerList = buyerService.getAllBuyer();
        Buyer buyerRec = buyerService.getBuyerById(id);
        model.addAttribute("buyerList", buyerList);
        model.addAttribute("buyerRec", buyerRec);
        return "buyer.html";
    }

    @PostMapping(value = { "/buyer/submit/", "/buyer/submit/{id}" }, params = { "add" })
    public String buyerAdd(@ModelAttribute("buyerInfo") Buyer buyerInfo) {
        buyerService.addBuyer(buyerInfo);
        return "redirect:/buyer";
    }

    @PostMapping(value = "/buyer/submit/{id}", params = { "edit" })
    public String buyerEdit(@ModelAttribute("buyerInfo") Buyer buyerInfo,
            @PathVariable("id") long id) {
        buyerService.updateBuyer(id, buyerInfo);
        return "redirect:/buyer";
    }

    @PostMapping(value = "/buyer/submit/{id}", params = { "delete" })
    public String buyerDelete(@PathVariable("id") long id) {
        buyerService.deleteBuyer(id);
        return "redirect:/buyer";
    }

    // ======================================
    // SELLER (LOGIN, LOGOUT, PAGE)
    // ======================================

    @GetMapping(value = "/login")
    public String sellerLoginPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            return "redirect:/seller";
        } else
            return "login.html";
    }

    @PostMapping(value = "/validateLogin")
    public String sellerLogin(Model model, @RequestParam(value = "kode") String kodeUser,
            @RequestParam(value = "password") String passUser, HttpServletRequest request) {

        Seller S = sellerService.getSellerByKode(kodeUser);

        model.addAttribute("userRec", S);
        if (S != null && passUser.equals(S.getPassword())) {
            request.getSession().setAttribute("Seller", S);
            model.addAttribute("userRec", S);
            return "redirect:/seller";
        } else
            return "redirect:/login";
    }

    @GetMapping(value = "/sellerlogout")
    public String sellerLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }

    @GetMapping(value = { "/seller", "/seller/" })
    public String SellerPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            List<Seller> sellerList = sellerService.getAllSeller();
            model.addAttribute("sellerList", sellerList);
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            return "seller.html";
        } else
            return "redirect:/login";
    }

    @PostMapping("/seller")
    public String saveSeller(@ModelAttribute Seller seller, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") == null) {
            return "redirect:/login";
        }

        // Check if ID exists to determine Add or Update
        if (seller.getId() != null && seller.getId() > 0) {
            sellerService.updateSeller(seller.getId(), seller);
        } else {
            sellerService.addSeller(seller);
        }
        return "redirect:/seller";
    }

    @GetMapping("/seller/delete")
    public String deleteSeller(@RequestParam long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") == null) {
            return "redirect:/login";
        }
        sellerService.deleteSeller(id);
        return "redirect:/seller";
    }

    @GetMapping("/seller/edit")
    public String editSeller(@RequestParam long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") == null) {
            return "redirect:/login";
        }
        Seller s = sellerService.getSellerById(id);
        model.addAttribute("sellerRec", s);

        List<Seller> sellerList = sellerService.getAllSeller();
        model.addAttribute("sellerList", sellerList);
        model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
        return "seller.html";
    }
}