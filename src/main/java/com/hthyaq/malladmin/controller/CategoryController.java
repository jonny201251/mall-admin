package com.hthyaq.malladmin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.utils.cascade.CascadeUtil;
import com.hthyaq.malladmin.common.utils.cascade.CascadeView;
import com.hthyaq.malladmin.common.utils.tree.TreeUtil;
import com.hthyaq.malladmin.common.utils.tree.TreeView;
import com.hthyaq.malladmin.common.utils.treeSelect.TreeSelectUtil;
import com.hthyaq.malladmin.common.utils.treeSelect.TreeSelectView;
import com.hthyaq.malladmin.model.entity.Category;
import com.hthyaq.malladmin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品的类目表 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/category")
@ResponseResult
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public IPage<Category> list(String json) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        return categoryService.page(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @GetMapping("/treeSelect")
    public List<TreeSelectView> treeSelect() {
        List<Category> list = categoryService.list(new QueryWrapper<Category>().eq("status", 1).orderByAsc("sort").orderByDesc("id"));
        return TreeSelectUtil.get(list);
    }

    @GetMapping("/tree")
    public List<TreeView> tree() {
        List<Category> list = categoryService.list(new QueryWrapper<Category>().eq("status", 1).orderByAsc("sort").orderByDesc("id"));
        return TreeUtil.get(list);
    }

    @GetMapping("/cascade")
    public List<CascadeView> cascade() {
        List<Category> list = categoryService.list(new QueryWrapper<Category>().eq("status", 1).orderByAsc("sort").orderByDesc("id"));
        return CascadeUtil.get(list);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Category category) {
        boolean flag = true;
        //处理一下sort,默认等于id
        flag = categoryService.save(category);
        category.setSort(Double.valueOf(category.getId()));
        flag = categoryService.updateById(category);
        return flag;
    }

    @GetMapping("/getById")
    public Category getById(Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody Category category) {
        return categoryService.updateById(category);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        List<Category> list = categoryService.list(new QueryWrapper<Category>().eq("pid", id));
        if (list.size() > 0) {
            throw new RuntimeException("请先删除该节点下的所有节点");
        } else {
            return categoryService.removeById(id);
        }
    }

    //根据categoryId获取所有父级节点的名称,以后这个这个重写
    @GetMapping("/categoryNames")
    public List<String> categoryNames(Integer categoryId) {
        List<String> list = Lists.newArrayList();
        String tmp1 = null;
        String tmp2 = null;
        String tmp3 = null;
        Category catTmp3 = categoryService.getById(categoryId);
        if (catTmp3 != null) {
            tmp3 = catTmp3.getName();
            if (catTmp3.getPid() != 0) {
                Category catTmp2 = categoryService.getById(catTmp3.getPid());
                tmp2 = catTmp2.getName();
                if (catTmp2.getPid() != 0) {
                    Category catTmp1 = categoryService.getById(catTmp2.getPid());
                    tmp1 = catTmp1.getName();
                }
            }
        }

        if (!Strings.isNullOrEmpty(tmp1)) {
            list.add(tmp1);
        }
        if (!Strings.isNullOrEmpty(tmp2)) {
            list.add(tmp2);
        }
        if (!Strings.isNullOrEmpty(tmp3)) {
            list.add(tmp3);
        }
        return list;
    }

}
