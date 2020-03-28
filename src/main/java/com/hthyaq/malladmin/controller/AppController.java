package com.hthyaq.malladmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.constants.GlobalConstants;
import com.hthyaq.malladmin.common.utils.StringLastUtil;
import com.hthyaq.malladmin.model.entity.Spu;
import com.hthyaq.malladmin.model.vo.AppSpuView;
import com.hthyaq.malladmin.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app")
@ResponseResult
//手机app的访问地址
public class AppController {
    @Autowired
    SpuService spuService;


    @GetMapping("/lunbo")
    public List<AppSpuView> lunbo() {
        List<AppSpuView> list = Lists.newArrayList();
        List<Spu> spuList = spuService.list(new QueryWrapper<Spu>().in("id", 3, 16, 34));
        for (Spu spu : spuList) {
            AppSpuView spuView = new AppSpuView();
            spuView.setSpuId(spu.getId());
            spuView.setImage(GlobalConstants.HOST_PATH + StringLastUtil.get(spu.getImages()));
            list.add(spuView);
        }
        return list;
    }

    @GetMapping("/totalPage")
    public Integer totalPage(@RequestParam(defaultValue = "") String keyword, Integer currentPage, Integer pageSize) {
        int totalPage = 0;
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        if (Strings.isNullOrEmpty(keyword)) {
            queryWrapper.orderByDesc("id");
        } else {
            queryWrapper.like("title", keyword);
        }
        IPage<Spu> page = spuService.page(new Page<>(currentPage, pageSize), queryWrapper);
        int total = (int) page.getTotal();
        //计算总页数
        totalPage = total / pageSize + ((total % pageSize == 0) ? 0 : 1);
        return totalPage;
    }

    @GetMapping("/spu")
    public List<AppSpuView> spu(@RequestParam(defaultValue = "") String keyword, Integer currentPage, Integer pageSize) {
        List<AppSpuView> list = Lists.newArrayList();
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        if (Strings.isNullOrEmpty(keyword)) {
            queryWrapper.orderByDesc("id");
        } else {
            queryWrapper.like("title", keyword);
        }
        IPage<Spu> page = spuService.page(new Page<>(currentPage, pageSize), queryWrapper);
        List<Spu> spuList = page.getRecords();
        for (Spu spu : spuList) {
            AppSpuView spuView = new AppSpuView();
            spuView.setSpuId(spu.getId());
            spuView.setImage(GlobalConstants.HOST_PATH + StringLastUtil.get(spu.getImages()));
            spuView.setTitle(spu.getTitle());
            spuView.setTmpPrice(spu.getTmpPrice());
            list.add(spuView);
        }
        return list;
    }

}
