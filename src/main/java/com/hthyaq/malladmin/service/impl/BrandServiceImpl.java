package com.hthyaq.malladmin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.hthyaq.malladmin.common.utils.UploadImageUtil;
import com.hthyaq.malladmin.mapper.BrandMapper;
import com.hthyaq.malladmin.model.entity.Brand;
import com.hthyaq.malladmin.model.entity.CategoryBrand;
import com.hthyaq.malladmin.model.vo.BrandView;
import com.hthyaq.malladmin.service.BrandService;
import com.hthyaq.malladmin.service.CategoryBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 商品的品牌表 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Autowired
    private CategoryBrandService categoryBrandService;

    @Override
    public boolean add(BrandView brandView, MultipartFile imageFile) throws IOException {
        boolean flag;
        if (imageFile != null) {
            String dbPath = UploadImageUtil.save(imageFile,"brand");
            //设置
            brandView.setImage(dbPath);
        }
        //品牌管理
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandView, brand);
        //设置categoryIds
        brand.setCategoryIds(ArrayUtil.join(brandView.getCategoryArr(), ","));
        //品牌的首字母大写
        if (!Strings.isNullOrEmpty(brand.getLetter())) {
            brand.setLetter(brand.getLetter().substring(0,1).toUpperCase());
        }
        flag = this.save(brand);
        //处理一下品牌的sort,默认等于id
        if (brandView.getSort() == null) {
            brand.setSort(Double.valueOf(brand.getId()));
        }
        flag = flag && this.updateById(brand);
        //类目-品牌管理
        CategoryBrand categoryBrand = new CategoryBrand();
        categoryBrand.setBrandId(brand.getId());
        //获取类目的id
        Integer[] categoryArr = brandView.getCategoryArr();
        if (categoryArr != null && categoryArr.length > 0) {
            Integer categoryId = categoryArr[categoryArr.length - 1];
            categoryBrand.setCategoryId(categoryId);
        }
        flag = flag && categoryBrandService.save(categoryBrand);
        return flag;
    }

    @Override
    public boolean edit(BrandView brandView, MultipartFile imageFile) throws IOException {
        boolean flag;
        //先删除后添加
        flag = this.removeById(brandView.getId());
        flag = flag && categoryBrandService.remove(new QueryWrapper<CategoryBrand>().eq("brand_id", brandView.getId()));
        flag = flag && this.add(brandView, imageFile);
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        boolean flag;
        flag = this.removeById(id);
        flag = flag && categoryBrandService.remove(new QueryWrapper<CategoryBrand>().eq("brand_id", id));
        return flag;
    }
}
