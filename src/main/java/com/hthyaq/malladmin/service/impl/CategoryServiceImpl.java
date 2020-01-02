package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.Category;
import com.hthyaq.malladmin.mapper.CategoryMapper;
import com.hthyaq.malladmin.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品的类目表 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
