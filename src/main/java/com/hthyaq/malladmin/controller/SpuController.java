package com.hthyaq.malladmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.constants.GlobalConstants;
import com.hthyaq.malladmin.common.utils.UploadImageUtil;
import com.hthyaq.malladmin.model.entity.SpecificationParam;
import com.hthyaq.malladmin.model.entity.SpecificationParam2;
import com.hthyaq.malladmin.model.responseResult.GlobalResponseResult;
import com.hthyaq.malladmin.model.vo.LabelName;
import com.hthyaq.malladmin.service.SpecificationGroupService;
import com.hthyaq.malladmin.service.SpecificationParam2Service;
import com.hthyaq.malladmin.service.SpecificationParamService;
import com.hthyaq.malladmin.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品的spu表，该表描述的是一个抽象性的商品 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuService spuService;
    @Autowired
    private SpecificationGroupService specificationGroupService;
    @Autowired
    private SpecificationParamService specificationParamService;
    @Autowired
    private SpecificationParam2Service specificationParam2Service;

    //将富文本编辑器的图片存储起来
    @PostMapping("/uploadImage")
    public GlobalResponseResult uploadImage(MultipartFile imageFile) throws IOException {
        String dbPath = UploadImageUtil.save(imageFile);
        return GlobalResponseResult.success(dbPath);
    }

    @PostMapping("/add")
    @ResponseResult
    public boolean add(MultipartFile[] images, String description, String form, String genericSpec) throws IOException {
        return spuService.add(images,description,form,genericSpec);
    }

    //根据categoryId返回规格类型
    @GetMapping("/specType")
    public GlobalResponseResult specType(Integer categoryId) {
        return GlobalResponseResult.success(spuService.getSpecType(categoryId));
    }


    //根据categoryId获取商品的通用规格和sku特有规格
    @GetMapping("/specAll")
    @ResponseResult
    public Map<String, Object> specAll(Integer categoryId) {
        Map<String, Object> map = Maps.newHashMap();
        String specType = spuService.getSpecType(categoryId);
        if (GlobalConstants.complexSpecHave.equals(specType)) {
            //复杂规格-通用属性
            List<LabelName> genericList = Lists.newArrayList();
            //复杂规格-sku特有属性
            List<LabelName> specialList = Lists.newArrayList();
            List<SpecificationParam> paramList = specificationParamService.list(new QueryWrapper<SpecificationParam>().eq("category_id", categoryId).orderByAsc("id"));
            for (SpecificationParam tmp : paramList) {
                if (tmp.getGeneric().equals(1)) {
                    genericList.add(new LabelName(tmp.getName(), tmp.getId()));
                } else {
                    specialList.add(new LabelName(tmp.getName(), tmp.getId()));
                }
            }
            if (genericList.size() > 0) {
                map.put("genericSpec", genericList);
            }
            if (specialList.size() > 0) {
                map.put("specialSpec", specialList);
            }
        } else if (GlobalConstants.complexSpecNo.equals(specType)) {
            //复杂规格-通用属性
            List<LabelName> genericList = Lists.newArrayList();
            List<SpecificationParam> paramList = specificationParamService.list(new QueryWrapper<SpecificationParam>().eq("category_id", categoryId).orderByAsc("id"));
            paramList.forEach(tmp -> genericList.add(new LabelName(tmp.getName(), tmp.getId())));
            if (genericList.size() > 0) {
                map.put("genericSpec", genericList);
            }
        } else if (GlobalConstants.easySpec.equals(specType)) {
            //简单规格-通用属性
            List<LabelName> genericList = Lists.newArrayList();
            List<SpecificationParam2> paramList = specificationParam2Service.list(new QueryWrapper<SpecificationParam2>().eq("category_id", categoryId).orderByAsc("id"));
            paramList.forEach(tmp -> genericList.add(new LabelName(tmp.getName(), tmp.getId())));
            if (genericList.size() > 0) {
                map.put("genericSpec", genericList);
            }
        }
        return map;
    }
}
