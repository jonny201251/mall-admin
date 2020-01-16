package com.hthyaq.malladmin.controller;


import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.model.dto.OrderDTO;
import com.hthyaq.malladmin.model.entity.OrderInfo;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 订单表，包括用户信息、收货地址 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/order")
@ResponseResult
@Slf4j
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/create")
    public Long createOrder(HttpSession httpSession, @RequestBody OrderDTO orderDTO) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        try {
            return orderInfoService.createOrder(user, orderDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyExceptionNotCatch("[创建订单] 创建订单失败");
        }
    }

    @GetMapping("/queryById")
    public OrderInfo queryById(Long id) {
        return orderInfoService.queryById(id);
    }

    @GetMapping("/orderList")
    public List<OrderInfo> orderList(HttpSession httpSession,Integer currentPage,Integer pageSize){
        SysUser user = (SysUser) httpSession.getAttribute("user");
        return orderInfoService.getOrderList(user.getId(),currentPage,pageSize);
    }
}
