package com.hthyaq.malladmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hthyaq.malladmin.model.entity.MoneyLimit;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.MoneyLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 159分厂的订单金额限制 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/moneyLimit")
public class MoneyLimitController {
    @Autowired
    MoneyLimitService moneyLimitService;

    //根据userId查询出一季度的金额
    @GetMapping("/getMoney")
    public Double getMoney(HttpSession httpSession) {
        SysUser user = (SysUser) httpSession.getAttribute("user");
        Integer companyId = user.getCompanyId();
        MoneyLimit moneyLimit = moneyLimitService.getOne(new QueryWrapper<MoneyLimit>().eq("company_id", companyId).eq("quarter", 0));
        return moneyLimit == null ? 0.0 : moneyLimit.getMoney();
    }
}
