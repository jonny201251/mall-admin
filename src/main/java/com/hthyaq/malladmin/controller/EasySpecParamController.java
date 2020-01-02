package com.hthyaq.malladmin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.entity.SpecificationParam2;
import com.hthyaq.malladmin.service.SpecificationParam2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *简单规格
 * @author zhangqiang
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/easySpecParam")
@ResponseResult
public class EasySpecParamController {
    @Autowired
    private SpecificationParam2Service specificationParam2Service;

    @GetMapping("/list")
    public IPage<SpecificationParam2> list(String json, Integer categoryId) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        QueryWrapper<SpecificationParam2> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId).orderByAsc("sort");

        return specificationParam2Service.page(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody SpecificationParam2 specificationParam2) {
        boolean flag = true;
        flag = specificationParam2Service.save(specificationParam2);
        specificationParam2.setSort(Double.valueOf(specificationParam2.getId()));
        flag = specificationParam2Service.updateById(specificationParam2);
        return flag;
    }

    @GetMapping("/getById")
    public SpecificationParam2 getById(Integer id) {
        return specificationParam2Service.getById(id);
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody SpecificationParam2 SpecificationParam2) {
        return specificationParam2Service.updateById(SpecificationParam2);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return specificationParam2Service.removeById(id);
    }
}
