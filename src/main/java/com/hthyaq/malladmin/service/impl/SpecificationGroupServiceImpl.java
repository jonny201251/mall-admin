package com.hthyaq.malladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.model.entity.SpecificationGroup;
import com.hthyaq.malladmin.mapper.SpecificationGroupMapper;
import com.hthyaq.malladmin.model.entity.SpecificationParam;
import com.hthyaq.malladmin.model.vo.SpecialGroupView;
import com.hthyaq.malladmin.service.SpecificationGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 规格参数的分组表、规格参数组、规格组 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@Service
public class SpecificationGroupServiceImpl extends ServiceImpl<SpecificationGroupMapper, SpecificationGroup> implements SpecificationGroupService {
    @Autowired
    private SpecificationParamServiceImpl specificationParamService;

    @Override
    public List<SpecialGroupView> getSpecialByCategoryId(Integer categoryId) {
        List<SpecialGroupView> list = Lists.newArrayList();
        List<SpecificationGroup> groupList = this.list(new QueryWrapper<SpecificationGroup>().eq("category_id", categoryId));
        for (SpecificationGroup specificationGroup : groupList) {
            SpecialGroupView specialGroupView = new SpecialGroupView();
            //拷贝
            BeanUtils.copyProperties(specificationGroup, specialGroupView);
            //查询出 特有规格
            List<SpecificationParam> paramList = specificationParamService.list(new QueryWrapper<SpecificationParam>().eq("group_id", specificationGroup.getId()));
            if (paramList.size() > 0) {
                specialGroupView.setParams(paramList);
            }
            //添加
            list.add(specialGroupView);
        }
        return list;
    }
}
