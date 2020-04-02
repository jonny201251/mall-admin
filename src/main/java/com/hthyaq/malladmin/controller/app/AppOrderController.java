package com.hthyaq.malladmin.controller.app;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Sets;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.model.bean.Cart;
import com.hthyaq.malladmin.model.dto.OrderDTO;
import com.hthyaq.malladmin.model.entity.OrderInfo;
import com.hthyaq.malladmin.model.entity.OrderStatus;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.CartService;
import com.hthyaq.malladmin.service.OrderInfoService;
import com.hthyaq.malladmin.service.OrderStatusService;
import com.hthyaq.malladmin.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/app/order")
@ResponseResult
public class AppOrderController {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private CartService cartService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "cart:uid:";

    @GetMapping("/list")
    public List<Cart> list(Integer userId, String skuIds) {
        Set<Long> skuSet = Sets.newHashSet();
        String[] tmp = skuIds.split(",");
        for (String s : tmp) {
            skuSet.add(Long.parseLong(s));
        }
        //取出登录用户
        SysUser user = sysUserService.getById(userId);
        //根据skuIds从购物车中取出cart
        String key = KEY_PREFIX + user.getId();
        if (!redisTemplate.hasKey(key)) {
            throw new MyExceptionNotCatch("SKU商品不存在！");
        }
        // 获取登录用户的所有购物车
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);

        List<Cart> carts = operation.values().stream()
                .map(o -> JSON.parseObject(o.toString(), Cart.class))
                .filter(cart -> skuSet.contains(cart.getSkuId()))
                .collect(Collectors.toList());
        return carts;
    }

    //提交订单
    @GetMapping("/create")
    public List<Long> createOrder(Integer userId, String skuIds) {

        //取出登录用户
        SysUser user = sysUserService.getById(userId);
        //填充orderDTO
        OrderDTO orderDTO = new OrderDTO();
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
    public IPage<OrderInfo> orderList(Integer userId, Integer currentPage, Integer pageSize, String orderId, String companyId, String status) {
        SysUser user = sysUserService.getById(userId);
        IPage<OrderInfo> page = orderInfoService.getOrderList(user, currentPage, pageSize, orderId, companyId, status);
        return page;
    }

    //取消订单
    @GetMapping("/orderStatus")
    public boolean orderCancel(String orderId, String status) {
        boolean flag;
        OrderStatus orderStatus = orderStatusService.getOne(new QueryWrapper<OrderStatus>().eq("order_id", orderId));
        orderStatus.setStatus(Integer.parseInt(status));
        flag = orderStatusService.updateById(orderStatus);
        return flag;
    }

    @GetMapping("/delete")
    public boolean delete(Integer userId, Long skuId) {
        //取出登录用户
        SysUser user = sysUserService.getById(userId);
        String key = KEY_PREFIX + user.getId();
        // 删除
        redisTemplate.opsForHash().delete(key, skuId.toString());
        return true;
    }
}
