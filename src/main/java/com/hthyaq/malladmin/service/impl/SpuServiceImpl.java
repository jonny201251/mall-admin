package com.hthyaq.malladmin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hthyaq.malladmin.common.constants.GlobalConstants;
import com.hthyaq.malladmin.common.utils.UploadImageUtil;
import com.hthyaq.malladmin.mapper.SpuMapper;
import com.hthyaq.malladmin.model.entity.*;
import com.hthyaq.malladmin.model.vo.SkuView;
import com.hthyaq.malladmin.model.vo.SpecSellerDefineView;
import com.hthyaq.malladmin.model.vo.SpecialSpecView;
import com.hthyaq.malladmin.model.vo.Value;
import com.hthyaq.malladmin.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品的spu表，该表描述的是一个抽象性的商品 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {
    @Autowired
    private SpecificationGroupService specificationGroupService;
    @Autowired
    private SpecificationParamService specificationParamService;
    @Autowired
    private SpuDetailService spuDetailService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private SpecSellerDefineService specSellerDefineService;

    //规格类型--以后放入缓存中
    public String getSpecType(Integer categoryId) {
        //默认为简单规格
        String type = GlobalConstants.easySpec;
        //复杂规格
        List<SpecificationGroup> groupList = specificationGroupService.list(new QueryWrapper<SpecificationGroup>().eq("category_id", categoryId));
        if (groupList.size() > 0) {
            type = GlobalConstants.complexSpecNo;
            List<SpecificationParam> paramList = specificationParamService.list(new QueryWrapper<SpecificationParam>().eq("category_id", categoryId).eq("generic", 0));
            if (paramList.size() > 0) {
                type = GlobalConstants.complexSpecHave;
            }
        }
        return type;
    }

    @Override
    public Boolean add(MultipartFile[] images, String description, String form, String genericSpec) throws IOException {
        boolean flag = true;
        JSONObject jsonObject = JSON.parseObject(form);
        Spu spuView = JSON.parseObject(form, Spu.class);
        String specType = this.getSpecType(spuView.getCategoryId());
        if (GlobalConstants.complexSpecHave.equals(specType)) {
            //1.spu
            Spu spu = new Spu();
            BeanUtils.copyProperties(spuView, spu);
            //spu的images
            if (images.length > 0) {
                List<String> list = Lists.newArrayList();
                for (MultipartFile imageFile : images) {
                    String dbPath = UploadImageUtil.save(imageFile);
                    list.add(dbPath);
                }
                spu.setImages(Joiner.on(",").join(list));
            }
            flag = this.save(spu);
            //获取spu的id
            Long spuId = spu.getId();
            //2.spu_detail
            SpuDetail spuDetail = new SpuDetail();
            spuDetail.setSpuId(spuId);
            spuDetail.setDescription(description);
            spuDetail.setPackingList(jsonObject.getString("packingList"));
            spuDetail.setAfterService(jsonObject.getString("afterService"));
            //spu_detail的generic_spec
            if (!Strings.isNullOrEmpty(genericSpec)) {
                spuDetail.setGenericSpec(genericSpec);
            }
            //spu_detail的special_spec
            Map<String, List<String>> specialSpecMap = Maps.newHashMap();
            List<SpecificationParam> paramList = specificationParamService.list(new QueryWrapper<SpecificationParam>().eq("category_id", spu.getCategoryId()).eq("generic", 0).orderByAsc("id"));
            for (SpecificationParam specificationParam : paramList) {
                Integer id = specificationParam.getId();
                SpecialSpecView specialSpecView = JSON.parseObject(jsonObject.getString(id + ""), SpecialSpecView.class);
                List<Value> dataSource = specialSpecView.getDataSource();
                if (dataSource.size() > 0) {
                    specialSpecMap.put(id + "", dataSource.stream().map(Value::getValue).collect(Collectors.toList()));
                }
            }
            if (specialSpecMap.size() > 0) {
                spuDetail.setSpecialSpec(JSON.toJSONString(specialSpecMap));
            }
            flag = flag && spuDetailService.save(spuDetail);
            //3.sku
            SkuView skuView = JSON.parseObject(jsonObject.getString("skuItem"), SkuView.class);
            List<Sku> skuList = skuView.getDataSource();
            if (skuList.size() > 0) {
                for (Sku sku : skuList) {
                    sku.setSpuId(spuId);
                    //title
                    StringBuilder title = new StringBuilder(spu.getTitle());
                    JSONObject skuSpecObject = JSON.parseObject(sku.getSkuSpec());
                    for (SpecificationParam specificationParam : paramList) {
                        Integer id = specificationParam.getId();
                        String specValue = skuSpecObject.getString(id + "");
                        if (!Strings.isNullOrEmpty(specValue)) {
                            title.append(" ").append(specValue);
                        }
                    }
                    sku.setTitle(title.toString());
                }
                flag = flag && skuService.saveBatch(skuList);
            }
            //4.specSellerDefine
            SpecSellerDefineView specSellerDefineView = JSON.parseObject(jsonObject.getString("specSellerDefine"), SpecSellerDefineView.class);
            List<SpecSellerDefine> specSellerDefineList = specSellerDefineView.getDataSource();
            if (specSellerDefineList.size() > 0) {
                specSellerDefineList.forEach(tmp -> tmp.setSpuId(spuId));
                flag = flag && specSellerDefineService.saveBatch(specSellerDefineList);
            }
        } else if (GlobalConstants.complexSpecNo.equals(specType)) {

        } else if (GlobalConstants.easySpec.equals(specType)) {

        }
        return flag;
    }

    @Override
    public Boolean addComplexItem(MultipartFile[] images, String description, String form, String genericSpec) {
        return null;
    }

    @Override
    public Boolean addEasyItem(MultipartFile[] images, String description, String form, String genericSpec) {
        return null;
    }
}
