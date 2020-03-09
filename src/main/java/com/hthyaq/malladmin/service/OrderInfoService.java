package com.hthyaq.malladmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hthyaq.malladmin.model.dto.OrderDTO;
import com.hthyaq.malladmin.model.entity.OrderInfo;
import com.hthyaq.malladmin.model.entity.SysUser;

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

    OrderInfo queryById(String orderId);

    IPage<OrderInfo> getOrderList(SysUser user, Integer currentPage, Integer pageSize, String orderId,String companyId);
}
