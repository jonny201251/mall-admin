package com.hthyaq.malladmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    @GetMapping("/address-page.html")
    public String addressPage() {
        return "front/address-page";
    }

    @GetMapping("/cart.html")
    public String cart() {
        return "front/cart";
    }

    @GetMapping("/center-collect.html")
    public String centerCollect() {
        return "front/center-collect";
    }

    @GetMapping("/center-footmark.html")
    public String centerFootmark() {
        return "front/center-footmark";
    }

    @GetMapping("/center-index.html")
    public String centerIndex() {
        return "front/center-index";
    }

    @GetMapping("/center-order-detail.html")
    public String centerOrderDetail() {
        return "front/center-order-detail";
    }

    @GetMapping("/center-order-evaluate.html")
    public String centerOrderEvaluate() {
        return "front/center-order-evaluate";
    }

    @GetMapping("/center-order-pay.html")
    public String centerOrderPay() {
        return "front/center-order-pay";
    }

    @GetMapping("/center-order-receive.html")
    public String centerOrderReceive() {
        return "front/center-order-receive";
    }

    @GetMapping("/center-order-send.html")
    public String centerOrderSend() {
        return "front/center-order-send";
    }

    @GetMapping("/center-setting-address.html")
    public String centerSettingAddress() {
        return "front/center-setting-address";
    }

    @GetMapping("/center-setting-address-complete.html")
    public String centerSettingAddressComplete() {
        return "front/center-setting-address-complete";
    }

    @GetMapping("/center-setting-address-phone.html")
    public String centerSettingAddressPhone() {
        return "front/center-setting-address-phone";
    }

    @GetMapping("/center-setting-info.html")
    public String centerSettingInfo() {
        return "front/center-setting-info";
    }

    @GetMapping("/center-setting-safe.html")
    public String centerSettingSafe() {
        return "front/center-setting-safe";
    }

    @GetMapping("/home.html")
    public String home() {
        return "front/home";
    }

    @GetMapping("/index.html")
    public String index() {
        return "front/index";
    }

    @GetMapping("/item.html")
    public String item() {
        return "front/item";
    }

    @GetMapping("/login.html")
    public String login() {
        return "front/login";
    }

    @GetMapping("/login-manage.html")
    public String loginManage() {
        return "front/login-manage";
    }

    @GetMapping("/mycomment.html")
    public String mycomment() {
        return "front/mycomment";
    }

    @GetMapping("/order.html")
    public String order() {
        return "front/order";
    }

    @GetMapping("/pay.html")
    public String pay() {
        return "front/pay";
    }

    @GetMapping("/payconfirm.html")
    public String payconfirm() {
        return "front/payconfirm";
    }

    @GetMapping("/payfail.html")
    public String payfail() {
        return "front/payfail";
    }

    @GetMapping("/paysuccess.html")
    public String paysuccess() {
        return "front/paysuccess";
    }

    @GetMapping("/register.html")
    public String register() {
        return "front/register";
    }

    @GetMapping("/search.html")
    public String search() {
        return "front/search";
    }

    @GetMapping("/seckill-index.html")
    public String seckillindex() {
        return "front/seckill-index";
    }

    @GetMapping("/seckill-item.html")
    public String seckillitem() {
        return "front/seckill-item";
    }

    @GetMapping("/success.html")
    public String success() {
        return "front/success";
    }

    @GetMapping("/success-cart.html")
    public String successcart() {
        return "front/success-cart";
    }

    @GetMapping("/weixinpay.html")
    public String weixinpay() {
        return "front/weixinpay";
    }
}
