package com.hthyaq.malladmin.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.malladmin.model.entity.MoneyLimit;
import com.hthyaq.malladmin.model.vo.FactoryMoneyLimitView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryMoneyLimitService {
    @Autowired
    MoneyLimitService moneyLimitService;

    public boolean save(FactoryMoneyLimitView factoryMoneyLimitView) {
        List<MoneyLimit> moneyLimitViewList = factoryMoneyLimitView.getMoneyLimits().getDataSource();
        if (ObjectUtil.length(moneyLimitViewList) > 0) {
            //设置MoneyLimit的companyId
            moneyLimitViewList.forEach(moneyLimit -> moneyLimit.setCompanyId(factoryMoneyLimitView.getCompanyId()));
            return moneyLimitService.saveBatch(moneyLimitViewList);
        }
        return false;
    }

    public boolean edit(FactoryMoneyLimitView factoryMoneyLimitView) {
        boolean flag = false;
        //先删除，后插入
        QueryWrapper<MoneyLimit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", factoryMoneyLimitView.getCompanyId());
        flag = moneyLimitService.remove(queryWrapper);
        if (flag) {
            this.save(factoryMoneyLimitView);
        }
        return flag;
    }
}
