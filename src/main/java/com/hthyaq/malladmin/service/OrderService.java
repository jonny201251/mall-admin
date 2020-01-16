package com.hthyaq.malladmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hthyaq.malladmin.model.dto.OrderDTO;
import com.hthyaq.malladmin.model.entity.Order;
import com.hthyaq.malladmin.model.entity.SysUser;

/**
 * <p>
 * 订单表，包括用户信息、收货地址 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-15
 */
public interface OrderService extends IService<Order> {

    Long createOrder(SysUser user, OrderDTO orderDTO);

    Order queryById(Long id);
}
