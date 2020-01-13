package com.hthyaq.malladmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hthyaq.malladmin.model.entity.Category;

import java.util.List;

/**
 * <p>
 * 商品的类目表 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
public interface CategoryService extends IService<Category> {
    //根据categoryId获取所有父级节点的名称
    List<Category> getAllParenCategory(Integer categoryId);
}
