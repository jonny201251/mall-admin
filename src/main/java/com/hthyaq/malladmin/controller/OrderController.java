package com.hthyaq.malladmin.controller;


import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.dto.OrderDTO;
import com.hthyaq.malladmin.model.entity.Order;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 订单表，包括用户信息、收货地址 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-15
 */
@RestController
@RequestMapping("/order")
@ResponseResult
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Long createOrder(HttpSession httpSession, @RequestBody OrderDTO orderDTO) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        return orderService.createOrder(user, orderDTO);
    }

    @GetMapping("/queryById")
    public Order queryById(Long id) {
        return orderService.queryById(id);
    }
}
