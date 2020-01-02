package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.CategoryBrand;
import com.hthyaq.malladmin.mapper.CategoryBrandMapper;
import com.hthyaq.malladmin.service.CategoryBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * category和brand的中间表 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements CategoryBrandService {

}
