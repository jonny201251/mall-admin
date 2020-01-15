package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.Order;
import com.hthyaq.malladmin.mapper.OrderMapper;
import com.hthyaq.malladmin.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表，包括用户信息、收货地址 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
