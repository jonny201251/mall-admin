package com.hthyaq.malladmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.entity.Sku;
import com.hthyaq.malladmin.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 商品的sku表,该表表示具体的商品实体 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
@RestController
@RequestMapping("/sku")
public class SkuController {
    @Autowired
    SkuService skuService;

    @GetMapping("/getSkuByIds")
    @ResponseResult
    public List<Sku> getSkuByIds(String ids) {
        return skuService.list(new QueryWrapper<Sku>().in("id", Arrays.asList(ids.split(","))));
    }
}
