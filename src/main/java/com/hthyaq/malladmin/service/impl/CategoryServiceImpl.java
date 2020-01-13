package com.hthyaq.malladmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.mapper.CategoryMapper;
import com.hthyaq.malladmin.model.entity.Category;
import com.hthyaq.malladmin.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Category> getAllParenCategory(Integer categoryId) {
        List<Category> list = Lists.newArrayList();
        Category tmp1 = null;
        Category tmp2 = null;
        Category tmp3 = null;
        Category catTmp3 = this.getById(categoryId);
        if (catTmp3 != null) {
            tmp3 = catTmp3;
            if (catTmp3.getPid() != 0) {
                Category catTmp2 = this.getById(catTmp3.getPid());
                tmp2 = catTmp2;
                if (catTmp2.getPid() != 0) {
                    Category catTmp1 = this.getById(catTmp2.getPid());
                    tmp1 = catTmp1;
                }
            }
        }

        if (tmp1 != null) {
            list.add(tmp1);
        }
        if (tmp2 != null) {
            list.add(tmp2);
        }
        if (tmp3 != null) {
            list.add(tmp3);
        }

        return list;
    }
}
