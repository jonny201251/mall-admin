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
import java.time.LocalDateTime;

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
        QueryWrapper<MoneyLimit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", companyId);
        //获取当前的月份
        int month = LocalDateTime.now().getMonth().getValue();
        if (month <= 3) {
            queryWrapper.eq("quarter", 0);
        } else if (month <= 6) {
            queryWrapper.eq("quarter", 1);
        } else if (month <= 9) {
            queryWrapper.eq("quarter", 2);
        } else {
            queryWrapper.eq("quarter", 3);
        }
        MoneyLimit moneyLimit = moneyLimitService.getOne(queryWrapper);
        return moneyLimit == null ? 0.0 : moneyLimit.getMoney();
    }
}
