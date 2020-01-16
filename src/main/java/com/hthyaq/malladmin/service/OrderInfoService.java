package com.hthyaq.malladmin.service;

import com.hthyaq.malladmin.model.dto.OrderDTO;
import com.hthyaq.malladmin.model.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hthyaq.malladmin.model.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 订单表，包括用户信息、收货地址 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-16
 */
public interface OrderInfoService extends IService<OrderInfo> {

    Long createOrder(SysUser user, OrderDTO orderDTO);

    OrderInfo queryById(Long id);

    List<OrderInfo> getOrderList(Integer userId,Integer currentPage,Integer pageSize);
}
