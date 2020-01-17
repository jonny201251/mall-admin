package com.hthyaq.malladmin.controller;

import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.vo.FactoryUserView;
import com.hthyaq.malladmin.service.FactoryUserService;
import com.hthyaq.malladmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factoryUser")
@ResponseResult
public class FactoryUserController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    FactoryUserService factoryUserService;

    @PostMapping("/add")
    public boolean add(@RequestBody FactoryUserView factoryUserView) {
        return factoryUserService.save(factoryUserView);
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody FactoryUserView factoryUserView) {
        return factoryUserService.edit(factoryUserView);
    }
}
