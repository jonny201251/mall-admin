package com.hthyaq.malladmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    @GetMapping("/cart.html")
    public String cart() {
        return "front/cart";
    }
}
