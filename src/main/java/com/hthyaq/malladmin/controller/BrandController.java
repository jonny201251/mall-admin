package com.hthyaq.malladmin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.entity.Brand;
import com.hthyaq.malladmin.model.entity.CategoryBrand;
import com.hthyaq.malladmin.model.vo.BrandView;
import com.hthyaq.malladmin.model.vo.LabelValue;
import com.hthyaq.malladmin.service.BrandService;
import com.hthyaq.malladmin.service.CategoryBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品的品牌表 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/brand")
@ResponseResult
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryBrandService categoryBrandService;

    @GetMapping("/list")
    public IPage<Brand> list(String json) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        return brandService.page(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @PostMapping("/add")
    public boolean add(String form, MultipartFile[] files) throws IOException {
        BrandView brandView = JSON.parseObject(form, BrandView.class);
        MultipartFile imageFile = files.length > 0 ? files[0] : null;
        return brandService.add(brandView, imageFile);
    }

    @PostMapping("/edit")
    public boolean edit(String form, MultipartFile[] files) throws IOException {
        BrandView brandView = JSON.parseObject(form, BrandView.class);
        MultipartFile imageFile = files.length > 0 ? files[0] : null;
        return brandService.edit(brandView, imageFile);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return brandService.delete(id);
    }

    @GetMapping("/getById")
    public BrandView getById(Integer id) {
        Brand brand = brandService.getById(id);
        BrandView brandView = new BrandView();
        BeanUtils.copyProperties(brand, brandView);
        //设置商品类目,将字符串数组转为整数数组
        if (!Strings.isNullOrEmpty(brand.getCategoryIds())) {
            String[] stringArr = brand.getCategoryIds().split(",");
            Integer[] categoryArr = new Integer[stringArr.length];
            for (int i = 0; i < stringArr.length; i++) {
                categoryArr[i] = Integer.parseInt(stringArr[i]);
            }
            brandView.setCategoryArr(categoryArr);
        }
        return brandView;
    }

    //根据categoryId获取品牌的下拉选项
    @GetMapping("/selectOptions")
    public List<LabelValue> selectOptions(Integer categoryId) {
        List<LabelValue> list = Lists.newArrayList();
        //从中间表中查出brandId
        List<CategoryBrand> tmpList = categoryBrandService.list(new QueryWrapper<CategoryBrand>().eq("category_id", categoryId));
        if (tmpList.size() == 0) {
            return list;
//            throw new RuntimeException("该商品类目下还没有绑定品牌");
        }
        List<Integer> brandIdList = tmpList.stream().map(CategoryBrand::getBrandId).collect(Collectors.toList());
        //查出品牌
        List<Brand> brandList = brandService.list(new QueryWrapper<Brand>().in("id", brandIdList));
        for (Brand brand : brandList) {
            list.add(new LabelValue(brand.getName(), brand.getId()));
        }
        return list;
    }
}
