package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.OrderStatus;
import com.hthyaq.malladmin.mapper.OrderStatusMapper;
import com.hthyaq.malladmin.service.OrderStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单状态表 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-15
 */
@Service
public class OrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatus> implements OrderStatusService {

}
