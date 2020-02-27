package com.hthyaq.malladmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.model.entity.Category;
import com.hthyaq.malladmin.model.entity.SpecificationGroup;
import com.hthyaq.malladmin.model.entity.SpecificationParam;
import com.hthyaq.malladmin.service.CategoryService;
import com.hthyaq.malladmin.service.SpecificationGroupService;
import com.hthyaq.malladmin.service.SpecificationParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//初始化数据
@RestController
@RequestMapping("/init")
public class InitDataController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    SpecificationGroupService specificationGroupService;
    @Autowired
    SpecificationParamService specificationParamService;

    //规格参数
    @GetMapping("/spec")
    public boolean spec() {
        List<Category> list = categoryService.list(new QueryWrapper<Category>().eq("pid", 1));
        List<Category> list2 = categoryService.list(new QueryWrapper<Category>().in("pid", list.stream().map(Category::getId).collect(Collectors.toList())));
        for (Category category : list2) {
            Integer categoryId = category.getId();
            SpecificationGroup specificationGroup = new SpecificationGroup();
            specificationGroup.setCategoryId(categoryId);
            specificationGroup.setName("主体");
            boolean flag = specificationGroupService.save(specificationGroup);
            if (flag) {
                Integer groupId = specificationGroup.getId();
                SpecificationParam specificationParam1 = new SpecificationParam();
                specificationParam1.setCategoryId(categoryId);
                specificationParam1.setGroupId(groupId);
                specificationParam1.setName("规格型号");
                SpecificationParam specificationParam2 = new SpecificationParam();
                specificationParam2.setCategoryId(categoryId);
                specificationParam2.setGroupId(groupId);
                specificationParam2.setName("单位");
                List<SpecificationParam> ll = Lists.newArrayList();
                ll.add(specificationParam1);
                ll.add(specificationParam2);
                boolean flagg = specificationParamService.saveBatch(ll);
                if (!flagg) {
                    int i = 1 / 0;
                }
            } else {
                int i = 1 / 0;
            }
        }
        return true;
    }
}
