package com.hthyaq.malladmin.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.mapper.OrderMapper;
import com.hthyaq.malladmin.model.dto.CartDTO;
import com.hthyaq.malladmin.model.dto.OrderDTO;
import com.hthyaq.malladmin.model.entity.*;
import com.hthyaq.malladmin.model.enums.OrderStatusEnum;
import com.hthyaq.malladmin.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    ReceiveAddressService receiveAddressService;
    @Autowired
    SkuService skuService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderStatusService orderStatusService;

    @Override
    public Long createOrder(SysUser user, OrderDTO orderDTO) {
        // 1 新增订单
        Order order = new Order();
        // 1.1 订单编号，基本信息 -- 订单ID，雪花算法（snowflake）生成全局唯一的ID
        long orderId = IdUtil.getSnowflake(1, 1).nextId();
        order.setOrderId(orderId);
        order.setPaymentType(orderDTO.getPaymentType());

        // 1.2 用户信息
        order.setUserId(user.getId());
        order.setBuyerNick(user.getRealName());
        order.setBuyerRate(0);

        // 1.3 收货人地址信息
        ReceiveAddress addr = receiveAddressService.getById(orderDTO.getAddressId());
        order.setReceiver(addr.getRealName());//收货人
        order.setReceiverMobile(addr.getMobile());//收货人手机号码
        order.setReceiverAddress(addr.getAddress());//收货所在街道
        order.setReceiverState(addr.getState());//收货人所在省
        order.setReceiverCity(addr.getCity());//收货人所在城市
        order.setReceiverDistrict(addr.getDistrict());//收货人所在区
        order.setReceiverZip(addr.getZip());//收货人邮编

        // 1.4 金额
        Map<Long, Integer> numMap = orderDTO.getCarts()
                .stream().collect(Collectors.toMap(CartDTO::getSkuId, CartDTO::getNum));
        Set<Long> ids = numMap.keySet();
        Collection<Sku> skus = skuService.listByIds(ids);

        // 准备orderDetail集合
        List<OrderDetail> details = new ArrayList<>();

        double totalPrice = 0L;
        for (Sku sku : skus) {
            totalPrice += sku.getPrice() * numMap.get(sku.getId());

            //封装orderDetail
            OrderDetail detail = new OrderDetail();
            detail.setImage(StringUtils.substringBefore(sku.getImages(), ","));
            detail.setNum(numMap.get(sku.getId()));
            detail.setOrderId(orderId);
            detail.setSkuSpec(sku.getSkuSpec());
            detail.setPrice(sku.getPrice());
            detail.setSkuId(sku.getId());
            detail.setTitle(sku.getTitle());

            details.add(detail);
        }
        order.setTotalPay(totalPrice);
        order.setActualPay(totalPrice + order.getPostFee() - 0);// 实付金额= 总金额 + 邮费 - 优惠金额

        // 1.5 写入数据库
        boolean flag = this.save(order);
        if (!flag) {
            throw new MyExceptionNotCatch("[创建订单] 创建订单失败，orderID:" + orderId);
        }

        // 2 新增订单详情
        flag = orderDetailService.saveBatch(details);
        if (!flag) {
            throw new MyExceptionNotCatch("[创建订单] 创建订单失败，orderID:" + orderId);
        }

        // 3 新增订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setStatus(OrderStatusEnum.NO_PAY.code());
        flag = orderStatusService.save(orderStatus);
        if (!flag) {
            throw new MyExceptionNotCatch("[创建订单] 创建订单失败，orderID:" + orderId);
        }

        // 4 减库存 -- 需要调用商品微服务，传递商品id和数量两个参数
        return orderId;
    }

    @Override
    public Order queryById(Long id) {
        Order order = this.getById(id);
        if (order == null) {
            throw new MyExceptionNotCatch("没有发现订单");
        }

        // 查询订单详情
        List<OrderDetail> orderDetails = orderDetailService.list(new QueryWrapper<OrderDetail>().eq("order_id",id));
        if(CollectionUtils.isEmpty(orderDetails)){
            throw new MyExceptionNotCatch("没有发现订单详情");
        }
        order.setOrderDetails(orderDetails);

        // 查询订单状态
        OrderStatus orderStatus = orderStatusService.getOne(new QueryWrapper<OrderStatus>().eq("order_id",id));
        if(orderStatus == null){
            throw new MyExceptionNotCatch("没有发现订单状态");
        }
        order.setOrderStatus(orderStatus);

        return order;
    }
}
