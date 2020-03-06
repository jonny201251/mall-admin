package com.hthyaq.malladmin.controller;

import org.springframework.stereotype.Controller;

@Controller
public class BackController {
    //商城后台的入口
//    @GetMapping({"/user/**", "/*List", "/*Add", "/*Edit", "/ImageRichText", "/*Detail"})
    public String entry() {
        return "back/index";
    }
}
