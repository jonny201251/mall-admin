package com.hthyaq.malladmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.bean.ChildForm;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.model.vo.FactoryUserView;
import com.hthyaq.malladmin.service.CompanyService;
import com.hthyaq.malladmin.service.FactoryUserService;
import com.hthyaq.malladmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factoryUser")
@ResponseResult
public class FactoryUserController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    FactoryUserService factoryUserService;
    @Autowired
    CompanyService companyService;

    @PostMapping("/add")
    public boolean add(@RequestBody FactoryUserView factoryUserView) {
        return factoryUserService.save(factoryUserView);
    }

    //分厂的id
    @GetMapping("/getById")
    public FactoryUserView getById(Integer id) {
        FactoryUserView factoryUserView = new FactoryUserView();
        factoryUserView.setCompanyId(id);
        //取出sysUser
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", id);
        List<SysUser> userList = sysUserService.list(queryWrapper);
        //设置
        factoryUserView.setUsers(new ChildForm<SysUser>(userList));
        return factoryUserView;
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody FactoryUserView factoryUserView) {
        return factoryUserService.edit(factoryUserView);
    }

}
