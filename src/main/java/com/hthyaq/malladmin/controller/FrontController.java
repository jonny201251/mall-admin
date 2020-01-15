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

    @GetMapping("/foot.html")
    public String foot() {
        return "front/foot";
    }

    @GetMapping("/copyright.html")
    public String copyright() {
        return "front/copyright";
    }

    @GetMapping("/shortcut.html")
    public String shortcut() {
        return "front/shortcut";
    }

    @GetMapping("/item.html")
    public String item(Long id, Model model) {
        // 查询数据模型
        Map<String, Object> attributes = spuService.getItemData(id);
        // 准备数据模型
        model.addAllAttributes(attributes);
        // 返回视图
        return "front/item-" + attributes.get("specType");
    }

    @GetMapping("/getOrderInfo.html")
    public String getOrderInfo() {
        return "front/getOrderInfo";
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
