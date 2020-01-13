package com.hthyaq.malladmin.controller;

import com.hthyaq.malladmin.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class FrontController {
    @Autowired
    SpuService spuService;

    @GetMapping("/cart.html")
    public String cart() {
        return "front/cart";
    }

    @GetMapping("/item.html")
    public String item(Long id, Model model) {
        System.out.println(id);
        // 查询数据模型
        Map<String, Object> attributes = spuService.getItemData(id);
        // 准备数据模型
        model.addAllAttributes(attributes);
        // 返回视图
        return "front/item-" + attributes.get("specType");
    }

    @GetMapping("/login.html")
    public String login() {
        return "front/login";
    }

    @GetMapping("/login-manage.html")
    public String loginManage() {
        return "front/login-manage";
    }

    @GetMapping("/order.html")
    public String order() {
        return "front/order";
    }

    @GetMapping("/register.html")
    public String register() {
        return "front/register";
    }

    @GetMapping("/search.html")
    public String search() {
        return "front/search";
    }

}
