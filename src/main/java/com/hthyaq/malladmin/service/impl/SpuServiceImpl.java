package com.hthyaq.malladmin.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hthyaq.malladmin.common.constants.GlobalConstants;
import com.hthyaq.malladmin.common.utils.UploadImageUtil;
import com.hthyaq.malladmin.mapper.SpuMapper;
import com.hthyaq.malladmin.model.entity.*;
import com.hthyaq.malladmin.model.vo.*;
import com.hthyaq.malladmin.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
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
    private SpecificationParam2Service specificationParam2Service;
    @Autowired
    private SpuDetailService spuDetailService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private SpecSellerDefineService specSellerDefineService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CompanyService companyService;

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
    public Boolean add(SysUser user, MultipartFile[] images, String description, String form, String genericSpec) throws IOException {
        boolean flag = true;
        JSONObject jsonObject = JSON.parseObject(form);
        Spu spuView = JSON.parseObject(form, Spu.class);
        //设置spu的companyId
        spuView.setCompanyId(user.getCompany().getId());
        String specType = this.getSpecType(spuView.getCategoryId());
        if (GlobalConstants.complexSpecHave.equals(specType)) {
            //1.spu
            Spu spu = new Spu();
            BeanUtils.copyProperties(spuView, spu);
            //spu的images
            if (images.length > 0) {
                List<String> list = Lists.newArrayList();
                for (MultipartFile imageFile : images) {
                    String dbPath = UploadImageUtil.save(imageFile, "item");
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
                    //设置图片
                    if (!Strings.isNullOrEmpty(spu.getImages())) {
                        List<String> skuImagesList = Arrays.stream(spu.getImages().split(",")).map(image -> GlobalConstants.HOST_PATH + image).collect(Collectors.toList());
                        sku.setImages(Joiner.on(",").join(skuImagesList));
                    }
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
        } else if (GlobalConstants.complexSpecNo.equals(specType) || GlobalConstants.easySpec.equals(specType)) {
            //1.spu
            Spu spu = new Spu();
            BeanUtils.copyProperties(spuView, spu);
            //spu的images
            if (images.length > 0) {
                List<String> list = Lists.newArrayList();
                for (MultipartFile imageFile : images) {
                    String dbPath = UploadImageUtil.save(imageFile, "item");
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
            flag = flag && spuDetailService.save(spuDetail);
            //3.sku
            SkuView skuView = JSON.parseObject(jsonObject.getString("skuItem"), SkuView.class);
            List<Sku> skuList = skuView.getDataSource();
            if (skuList.size() > 0) {
                for (Sku sku : skuList) {
                    sku.setSpuId(spuId);
                    //设置图片
                    if (!Strings.isNullOrEmpty(spu.getImages())) {
                        List<String> skuImagesList = Arrays.stream(spu.getImages().split(",")).map(image -> GlobalConstants.HOST_PATH + image).collect(Collectors.toList());
                        sku.setImages(Joiner.on(",").join(skuImagesList));
                    }
                    sku.setTitle(spu.getTitle());
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
        }
        return flag;
    }

    @Override
    public Boolean edit(String oldImages, MultipartFile[] newImages, String description, String form, String genericSpec) throws IOException {
        boolean flag = true;
        JSONObject jsonObject = JSON.parseObject(form);
        Spu spuView = JSON.parseObject(form, Spu.class);
        String specType = this.getSpecType(spuView.getCategoryId());
        if (GlobalConstants.complexSpecHave.equals(specType)) {
            //1.spu
            Spu spu = new Spu();
            BeanUtils.copyProperties(spuView, spu);
            //spu的images
            if (newImages.length > 0) {
                List<String> list = Lists.newArrayList();
                for (MultipartFile imageFile : newImages) {
                    String dbPath = UploadImageUtil.save(imageFile, "item");
                    list.add(dbPath);
                }
                if (!Strings.isNullOrEmpty(oldImages)) {
                    spu.setImages(oldImages + "," + Joiner.on(",").join(list));
                } else {
                    spu.setImages(Joiner.on(",").join(list));
                }
            } else {
                spu.setImages(oldImages);
            }
            flag = this.updateById(spu);
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
            flag = flag && spuDetailService.updateById(spuDetail);
            //3.sku
            //先删除
            flag = flag && skuService.remove(new QueryWrapper<Sku>().eq("spu_id", spuId));
            SkuView skuView = JSON.parseObject(jsonObject.getString("skuItem"), SkuView.class);
            List<Sku> skuList = skuView.getDataSource();
            if (skuList.size() > 0) {
                for (Sku sku : skuList) {
                    sku.setSpuId(spuId);
                    //设置图片
                    if (!Strings.isNullOrEmpty(spu.getImages())) {
                        List<String> skuImagesList = Arrays.stream(spu.getImages().split(",")).map(image -> GlobalConstants.HOST_PATH + image).collect(Collectors.toList());
                        sku.setImages(Joiner.on(",").join(skuImagesList));
                    }
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
                //后保存
                flag = flag && skuService.saveBatch(skuList);
            }
            //4.specSellerDefine
            //先删除
            flag = flag && specSellerDefineService.remove(new QueryWrapper<SpecSellerDefine>().eq("spu_id", spuId));
            SpecSellerDefineView specSellerDefineView = JSON.parseObject(jsonObject.getString("specSellerDefine"), SpecSellerDefineView.class);
            List<SpecSellerDefine> specSellerDefineList = specSellerDefineView.getDataSource();
            if (specSellerDefineList.size() > 0) {
                specSellerDefineList.forEach(tmp -> tmp.setSpuId(spuId));
                //后保存
                flag = flag && specSellerDefineService.saveBatch(specSellerDefineList);
            }
        } else if (GlobalConstants.complexSpecNo.equals(specType) || GlobalConstants.easySpec.equals(specType)) {
            //1.spu
            Spu spu = new Spu();
            BeanUtils.copyProperties(spuView, spu);
            //spu的images
            if (newImages.length > 0) {
                List<String> list = Lists.newArrayList();
                for (MultipartFile imageFile : newImages) {
                    String dbPath = UploadImageUtil.save(imageFile, "item");
                    list.add(dbPath);
                }
                if (!Strings.isNullOrEmpty(oldImages)) {
                    spu.setImages(oldImages + "," + Joiner.on(",").join(list));
                } else {
                    spu.setImages(Joiner.on(",").join(list));
                }
            } else {
                spu.setImages(oldImages);
            }
            flag = this.updateById(spu);
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
            flag = flag && spuDetailService.updateById(spuDetail);
            //3.sku
            //先删除
            flag = flag && skuService.remove(new QueryWrapper<Sku>().eq("spu_id", spuId));
            SkuView skuView = JSON.parseObject(jsonObject.getString("skuItem"), SkuView.class);
            List<Sku> skuList = skuView.getDataSource();
            if (skuList.size() > 0) {
                for (Sku sku : skuList) {
                    sku.setSpuId(spuId);
                    //设置图片
                    if (!Strings.isNullOrEmpty(spu.getImages())) {
                        List<String> skuImagesList = Arrays.stream(spu.getImages().split(",")).map(image -> GlobalConstants.HOST_PATH + image).collect(Collectors.toList());
                        sku.setImages(Joiner.on(",").join(skuImagesList));
                    }
                    sku.setTitle(spu.getTitle());
                }
                //后保存
                flag = flag && skuService.saveBatch(skuList);
            }
            //4.specSellerDefine
            //先删除
            flag = flag && specSellerDefineService.remove(new QueryWrapper<SpecSellerDefine>().eq("spu_id", spuId));
            SpecSellerDefineView specSellerDefineView = JSON.parseObject(jsonObject.getString("specSellerDefine"), SpecSellerDefineView.class);
            List<SpecSellerDefine> specSellerDefineList = specSellerDefineView.getDataSource();
            if (specSellerDefineList.size() > 0) {
                specSellerDefineList.forEach(tmp -> tmp.setSpuId(spuId));
                //后保存
                flag = flag && specSellerDefineService.saveBatch(specSellerDefineList);
            }
        }
        return flag;
    }

    @Override
    public Map<String, Object> getItemData(Long spuId) {
        Map<String, Object> model = new HashMap<>();
        // 查询spu
        Spu spu = this.getById(spuId);
        // 查询skus
        List<Sku> skus = skuService.list(new QueryWrapper<Sku>().eq("spu_id", spuId));
        // 查询详情detail
        SpuDetail detail = spuDetailService.getById(spuId);
        // 查询brand
        Brand brand = brandService.getById(spu.getBrandId());
        if (brand == null) {
            brand = new Brand();
        }
        // 查询商品的分类
        List<Category> categories = categoryService.getAllParenCategory(spu.getCategoryId());
        String specType = this.getSpecType(spu.getCategoryId());
        model.put("specType", specType);
        // 查询-复杂规格参数
        if (GlobalConstants.complexSpecHave.equals(specType) || GlobalConstants.complexSpecNo.equals(specType)) {
            List<SpecialGroupView> specs = specificationGroupService.getSpecialByCategoryId(spu.getCategoryId());
            model.put("specs", specs);
        } else if (GlobalConstants.easySpec.equals(specType)) {
            List<SpecificationParam2> specs = specificationParam2Service.list(new QueryWrapper<SpecificationParam2>().eq("category_id", spu.getCategoryId()));
            model.put("specs", specs);
        }

        //所属公司
        Company company = companyService.getById(spu.getCompanyId());

        model.put("title", spu.getTitle());
        model.put("subTitle", spu.getSubTitle());
        model.put("skus", skus);
        model.put("detail", detail);
        model.put("brand", brand);
        model.put("categories", categories);
        model.put("company", company);
        return model;
    }

    @Override
    public SearchResult getSearchData(SearchRequest request) {
        SearchResult searchResult = new SearchResult();
        String key = request.getKey();
        int currentPage = request.getPage();
        int pageSize = request.getSize();
        //分页查询出spu
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        if (!Strings.isNullOrEmpty(key)) {
            queryWrapper.like("title", key);
        }
        //暂时处理 分类(cid3)、品牌(brandId)
        String categoryId = request.getFilter().get("cid3");
        String brandId = request.getFilter().get("brandId");
        if (!Strings.isNullOrEmpty(categoryId)) {
            queryWrapper.eq("category_id", Integer.parseInt(categoryId));
        }
        if (!Strings.isNullOrEmpty(brandId)) {
            queryWrapper.eq("brand_id", Integer.parseInt(brandId));
        }
        //处理一下商品的三级分类
        if (!Strings.isNullOrEmpty(request.getCid1())) {
            List<Category> cid2List = categoryService.list(new QueryWrapper<Category>().eq("pid", Integer.parseInt(request.getCid1())));
            List<Category> cid3List = categoryService.list(new QueryWrapper<Category>().in("pid", cid2List.stream().map(Category::getId).collect(Collectors.toList())));
            if (cid3List.size() > 0) {
                queryWrapper.in("category_id", cid3List.stream().map(Category::getId).collect(Collectors.toList()));
            }
        } else if (!Strings.isNullOrEmpty(request.getCid2())) {
            List<Category> cid3List = categoryService.list(new QueryWrapper<Category>().eq("pid", Integer.parseInt(request.getCid2())));
            if (cid3List.size() > 0) {
                queryWrapper.in("category_id", cid3List.stream().map(Category::getId).collect(Collectors.toList()));
            }
        } else if (!Strings.isNullOrEmpty(request.getCid3())) {
            queryWrapper.eq("category_id", Integer.parseInt(request.getCid3()));
        }

        IPage<Spu> spuPage = this.page(new Page<>(currentPage, pageSize), queryWrapper);
        List<Spu> spuList = spuPage.getRecords();
        //
        long total = spuPage.getTotal();
        long totalPage = total / pageSize + ((total % pageSize == 0) ? 0 : 1);
        searchResult.setTotal(total);
        searchResult.setTotalPage(totalPage);
        //
        List<SearchItems> itemList = Lists.newArrayList();
        if (spuList.size() > 0) {
            for (Spu spu : spuList) {
                SearchItems tmp = new SearchItems();
                tmp.setId(spu.getId());
                tmp.setTitle(spu.getTitle());
                tmp.setSubTitle(spu.getSubTitle());
                tmp.setBrandId(spu.getBrandId());
                List<Category> categoryList = categoryService.getAllParenCategory(spu.getCategoryId());
                for (int i = 0; i < categoryList.size(); i++) {
                    if (i == 0) {
                        tmp.setCid1(categoryList.get(i).getId());
                    } else if (i == 1) {
                        tmp.setCid2(categoryList.get(i).getId());
                    } else if (i == 2) {
                        tmp.setCid3(categoryList.get(i).getId());
                    }
                }
                tmp.setCreateTime(spu.getCreateTime());
                //查询出sku
                List<Sku> skuList = skuService.list(new QueryWrapper<Sku>().eq("spu_id", spu.getId()));

                Set<Double> priceSet = Sets.newHashSet();
                List<Map<String, Object>> skusList = Lists.newArrayList();
                for (Sku sku : skuList) {
                    priceSet.add(sku.getPrice());

                    Map<String, Object> skus = Maps.newHashMap();
                    skus.put("id", sku.getId());
                    skus.put("title", sku.getTitle());
                    skus.put("price", sku.getPrice());
                    skus.put("image", StringUtils.substringBefore(sku.getImages(), ","));//sku中有多个图片，只展示第一张
                    skusList.add(skus);
                }
                tmp.setPrice(priceSet);
                tmp.setSkus(JSONUtil.toJsonStr(skusList));

                itemList.add(tmp);
                //tmp的规格参数 暂时不做！！
            }
        }
        searchResult.setItems(itemList);
        List<Category> categoryList = Lists.newArrayList();
        if (spuList.size() > 0) {
            categoryList = categoryService.list(new QueryWrapper<Category>().in("id", spuList.stream().map(Spu::getCategoryId).collect(Collectors.toList())));
        }

        List<Brand> brandList = Lists.newArrayList();
        if (spuList.size() > 0) {
            brandList = brandService.list(new QueryWrapper<Brand>().in("id", spuList.stream().map(Spu::getBrandId).collect(Collectors.toList())));
        }

        searchResult.setCategories(categoryList);
        searchResult.setBrands(brandList);
        //searchResult的规格参数 暂时不做！！
        return searchResult;
    }

}
