package com.hthyaq.malladmin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.entity.SpecificationParam;
import com.hthyaq.malladmin.service.SpecificationParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 复杂规格组下的规格参数 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/complexSpecParam")
@ResponseResult
public class ComplexSpecParamController {
    @Autowired
    private SpecificationParamService specificationParamService;

    @GetMapping("/list")
    public IPage<SpecificationParam> list(String json, Integer categoryId, Integer groupId) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        QueryWrapper<SpecificationParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId).eq("group_id", groupId).orderByAsc("sort");

        return specificationParamService.page(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody SpecificationParam specificationParam) {
        boolean flag = true;
        flag = specificationParamService.save(specificationParam);
        specificationParam.setSort(Double.valueOf(specificationParam.getId()));
        flag = specificationParamService.updateById(specificationParam);
        return flag;
    }

    @GetMapping("/getById")
    public SpecificationParam getById(Integer id) {
        return specificationParamService.getById(id);
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody SpecificationParam SpecificationParam) {
        return specificationParamService.updateById(SpecificationParam);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return specificationParamService.removeById(id);
    }
}
