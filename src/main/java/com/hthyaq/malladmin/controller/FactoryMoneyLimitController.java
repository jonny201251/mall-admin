package com.hthyaq.malladmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.bean.ChildForm;
import com.hthyaq.malladmin.model.entity.MoneyLimit;
import com.hthyaq.malladmin.model.vo.FactoryMoneyLimitView;
import com.hthyaq.malladmin.service.CompanyService;
import com.hthyaq.malladmin.service.FactoryMoneyLimitService;
import com.hthyaq.malladmin.service.MoneyLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factoryMoneyLimit")
@ResponseResult
public class FactoryMoneyLimitController {
    @Autowired
    MoneyLimitService moneyLimitService;
    @Autowired
    FactoryMoneyLimitService factoryMoneyLimitService;
    @Autowired
    CompanyService companyService;

    @PostMapping("/add")
    public boolean add(@RequestBody FactoryMoneyLimitView factoryMoneyLimitView) {
        return factoryMoneyLimitService.save(factoryMoneyLimitView);
    }

    //分厂的id
    @GetMapping("/getById")
    public FactoryMoneyLimitView getById(Integer id) {
        FactoryMoneyLimitView factoryMoneyLimitView = new FactoryMoneyLimitView();
        factoryMoneyLimitView.setCompanyId(id);
        //取出MoneyList
        QueryWrapper<MoneyLimit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", id);
        List<MoneyLimit> moneyLimitList = moneyLimitService.list(queryWrapper);
        //设置
        factoryMoneyLimitView.setMoneyLimits(new ChildForm<>(moneyLimitList));
        return factoryMoneyLimitView;
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody FactoryMoneyLimitView factoryMoneyLimitView) {
        return factoryMoneyLimitService.edit(factoryMoneyLimitView);
    }

}
