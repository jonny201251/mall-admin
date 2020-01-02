package com.hthyaq.malladmin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionDemo {

    //设置session
    @GetMapping("/set")
    public String set(HttpSession httpSession) {
        httpSession.setAttribute("a", "a");
        return httpSession.getId();
    }

    //获取sessio
    @GetMapping("/get")
    public String get(HttpSession httpSession) {
        String data = httpSession.getId() + "," + httpSession.getAttribute("a");
        return data;
    }
}
