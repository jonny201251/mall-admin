package com.hthyaq.malladmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @GetMapping("/spu")
    public List<AppSpuView> spu(Integer currentPage, Integer pageSize) {
        List<AppSpuView> list = Lists.newArrayList();
        IPage<Spu> page = spuService.page(new Page<>(currentPage, pageSize), new QueryWrapper<Spu>().orderByDesc("id"));
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
