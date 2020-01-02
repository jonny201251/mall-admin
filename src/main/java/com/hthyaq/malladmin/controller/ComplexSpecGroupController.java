package com.hthyaq.malladmin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.entity.SpecificationGroup;
import com.hthyaq.malladmin.model.entity.SpecificationParam;
import com.hthyaq.malladmin.service.SpecificationGroupService;
import com.hthyaq.malladmin.service.SpecificationParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 复杂规格组
 * @author zhangqiang
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/complexSpecGroup")
@ResponseResult
public class ComplexSpecGroupController {
    @Autowired
    private SpecificationGroupService specificationGroupService;
    @Autowired
    private SpecificationParamService specificationParamService;

    @GetMapping("/list")
    public IPage<SpecificationGroup> list(String json, Integer categoryId) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        QueryWrapper<SpecificationGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId).orderByAsc("sort");

        return specificationGroupService.page(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody SpecificationGroup specificationGroup) {
        boolean flag = true;
        //处理一下sort,默认等于id
        flag = specificationGroupService.save(specificationGroup);
        specificationGroup.setSort(Double.valueOf(specificationGroup.getId()));
        flag = specificationGroupService.updateById(specificationGroup);
        return flag;
    }

    @GetMapping("/getById")
    public SpecificationGroup getById(Integer id) {
        return specificationGroupService.getById(id);
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody SpecificationGroup specificationGroup) {
        return specificationGroupService.updateById(specificationGroup);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        List<SpecificationParam> list = specificationParamService.list(new QueryWrapper<SpecificationParam>().eq("group_id", id));
        if (list.size() > 0) {
            throw new RuntimeException("请先删除该节点下的所有节点");
        } else {
            return specificationGroupService.removeById(id);
        }
    }
}
