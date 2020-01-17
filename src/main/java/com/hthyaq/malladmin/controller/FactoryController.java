package com.hthyaq.malladmin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.entity.Company;
import com.hthyaq.malladmin.model.vo.AntdSelect;
import com.hthyaq.malladmin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 公司表，包括公司、商家、供应商 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/factory")
@ResponseResult
public class FactoryController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/list")
    public IPage<Company> list(String json) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        return companyService.page(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Company company) {
        return companyService.save(company);
    }

    @GetMapping("/getById")
    public Company getById(Integer id) {
        return companyService.getById(id);
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody Company company) {
        company.setCreateTime(LocalDateTime.now());
        return companyService.updateById(company);
    }

    @GetMapping("/factoryList")
    public List<AntdSelect> getFactoryList() {
        return companyService.list().stream().map(company -> new AntdSelect(company.getName(), company.getId() + "")).collect(Collectors.toList());
    }

}
