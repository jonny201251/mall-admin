package com.hthyaq.malladmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.utils.StringLastUtil;
import com.hthyaq.malladmin.model.entity.Spu;
import com.hthyaq.malladmin.model.vo.SpuView;
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
    public List<SpuView> lunbo() {
        List<SpuView> list = Lists.newArrayList();
        List<Spu> spuList = spuService.list(new QueryWrapper<Spu>().in("id", 3, 16, 34));
        for (Spu spu : spuList) {
            SpuView spuView = new SpuView();
            spuView.setSpuId(spu.getId());
            spuView.setImage(StringLastUtil.get(spu.getImages()));
            list.add(spuView);
        }
        return list;
    }
}
