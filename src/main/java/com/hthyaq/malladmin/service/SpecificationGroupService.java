package com.hthyaq.malladmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hthyaq.malladmin.model.entity.SpecificationGroup;
import com.hthyaq.malladmin.model.vo.SpecialGroupView;

import java.util.List;

/**
 * <p>
 * 规格参数的分组表、规格参数组、规格组 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
public interface SpecificationGroupService extends IService<SpecificationGroup> {
    //获取页面需要的规格组、规格参数
    public List<SpecialGroupView> getSpecialByCategoryId(Integer categoryId);
}