package com.hthyaq.malladmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.model.dto.OrderDTO;
import com.hthyaq.malladmin.model.entity.OrderInfo;
import com.hthyaq.malladmin.model.entity.OrderStatus;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.OrderInfoService;
import com.hthyaq.malladmin.service.OrderStatusService;
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
    @Autowired
    private OrderStatusService orderStatusService;

    //用户提交订单
    @PostMapping("/create")
    public List<Long> createOrder(HttpSession httpSession, @RequestBody OrderDTO orderDTO) {
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
    public OrderInfo queryById(String orderId) {
        return orderInfoService.queryById(orderId);
    }

    @GetMapping("/orderList")
    public IPage<OrderInfo> orderList(HttpSession httpSession, Integer currentPage, Integer pageSize, String orderId,String companyId) {
        SysUser user = (SysUser) httpSession.getAttribute("user");
        IPage<OrderInfo> page = orderInfoService.getOrderList(user, currentPage, pageSize, orderId,companyId);
        return page;
    }

    //取消订单
    @GetMapping("/orderStatus")
    public boolean orderCancel(String orderId,String status) {
        boolean flag;
        OrderStatus orderStatus = orderStatusService.getOne(new QueryWrapper<OrderStatus>().eq("order_id", orderId));
        orderStatus.setStatus(Integer.parseInt(status));
        flag = orderStatusService.updateById(orderStatus);
        return flag;
    }
}
