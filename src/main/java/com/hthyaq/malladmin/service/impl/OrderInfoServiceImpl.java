package com.hthyaq.malladmin.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.mapper.OrderInfoMapper;
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

import static java.util.Optional.ofNullable;

/**
 * <p>
 * 订单表，包括用户信息、收货地址 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-16
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    @Autowired
    ReceiveAddressService receiveAddressService;
    @Autowired
    SkuService skuService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    CompanyService companyService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SpuService spuService;

    @Override
    public List<Long> createOrder(SysUser user, OrderDTO orderDTO) {
        List<Long> orderIdList = Lists.newArrayList();
        List<CartDTO> cartList = orderDTO.getCarts();
        Map<Integer, List<CartDTO>> map = Maps.newHashMap();
        for (CartDTO cartDTO : cartList) {
            //根据skuId获取companyId
            Sku sku = skuService.getById(cartDTO.getSkuId());
            Spu spu = spuService.getById(sku.getSpuId());
            Integer companyId = spu.getCompanyId();
            List<CartDTO> cartDTOList = map.get(companyId);
            if (cartDTOList != null) {
                cartDTOList.add(cartDTO);
            } else {
                cartDTOList = Lists.newArrayList();
                cartDTOList.add(cartDTO);
                map.put(companyId, cartDTOList);
            }
        }
        for (Map.Entry<Integer, List<CartDTO>> entry : map.entrySet()) {
            OrderDTO tmp = new OrderDTO();
            tmp.setAddressId(orderDTO.getAddressId());
            tmp.setPaymentType(orderDTO.getPaymentType());
            tmp.setCarts(entry.getValue());
            Long orderId = this.createOrderByCompany(user, tmp, entry.getKey());
            orderIdList.add(orderId);
        }
        return orderIdList;
    }

    //按照商家进行创建订单
    private Long createOrderByCompany(SysUser user, OrderDTO orderDTO, Integer companyId) {
        // 1 新增订单
        OrderInfo order = new OrderInfo();
        //1.0设置订单所属的商家
        order.setCompanyId(companyId);
        // 1.1 订单编号，基本信息 -- 订单ID，雪花算法（snowflake）生成全局唯一的ID
        long orderId = IdUtil.getSnowflake(1, 1).nextId();
        order.setOrderId(String.valueOf(orderId));
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
            detail.setOrderId(String.valueOf(orderId));
            detail.setSkuSpec(sku.getSkuSpec());
            detail.setPrice(sku.getPrice());
            detail.setSkuId(sku.getId());
            detail.setTitle(sku.getTitle());

            details.add(detail);
        }
        order.setTotalPay(totalPrice);
        order.setActualPay(totalPrice + ofNullable(order.getPostFee()).orElse(0.0) - 0);// 实付金额= 总金额 + 邮费 - 优惠金额

        // 1.5 写入数据库
        boolean flag = false;
        try {
            flag = this.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        orderStatus.setOrderId(String.valueOf(orderId));
        orderStatus.setStatus(OrderStatusEnum.NO_PAY.code());
        flag = orderStatusService.save(orderStatus);
        if (!flag) {
            throw new MyExceptionNotCatch("[创建订单] 创建订单失败，orderID:" + orderId);
        }

        // 4 减库存 -- 需要调用商品微服务，传递商品id和数量两个参数
        return orderId;
    }


    @Override
    public OrderInfo queryById(String orderId) {
        OrderInfo order = this.getById(orderId);
        if (order == null) {
            throw new MyExceptionNotCatch("没有发现订单");
        }
        this.setOrderOther(order);
        return order;
    }

    //设置订单的详情、状态、分厂名称
    private void setOrderOther(OrderInfo order) {
        // 查询订单详情
        List<OrderDetail> orderDetails = orderDetailService.list(new QueryWrapper<OrderDetail>().eq("order_id", order.getOrderId()));
        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new MyExceptionNotCatch("没有发现订单详情");
        }
        order.setOrderDetails(orderDetails);

        // 查询订单状态
        OrderStatus orderStatus = orderStatusService.getOne(new QueryWrapper<OrderStatus>().eq("order_id", order.getOrderId()));
        if (orderStatus == null) {
            throw new MyExceptionNotCatch("没有发现订单状态");
        }
        order.setOrderStatus(orderStatus);

        //分厂名称
        SysUser user = sysUserService.getById(order.getUserId());
        Company company = companyService.getById(user.getCompanyId());
        order.setCompany(company);
    }

    @Override
    public IPage<OrderInfo> getOrderList(SysUser user, Integer currentPage, Integer pageSize, String orderId, String companyId) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        if (!Strings.isNullOrEmpty(orderId)) {
            queryWrapper.like("order_id", orderId);
        }
        if (user.getCompany().getType() == 0) {
            //159厂
            if (Strings.isNullOrEmpty(companyId)) {
                //1.取出159分厂的id
                List<Company> companyList = companyService.list(new QueryWrapper<Company>().eq("type", 1));
                //2.取出分厂人员的id
                List<SysUser> userList = sysUserService.list(new QueryWrapper<SysUser>().in("company_id", companyList.stream().map(Company::getId).collect(Collectors.toList())));

                queryWrapper.in("user_id", userList.stream().map(SysUser::getId).collect(Collectors.toList()));
            } else {
                List<SysUser> userList = sysUserService.list(new QueryWrapper<SysUser>().in("company_id", Integer.parseInt(companyId)));
                queryWrapper.in("user_id", userList.stream().map(SysUser::getId).collect(Collectors.toList()));
            }
        } else if (user.getCompany().getType() == 1) {
            //159分厂
            queryWrapper.eq("user_id", user.getId());
        } else if (user.getCompany().getType() == 2) {
            //供应商
            queryWrapper.eq("company_id", user.getCompanyId());
        }
        //获取分页的订单
        IPage<OrderInfo> page = this.page(new Page<>(currentPage, pageSize), queryWrapper);
        List<OrderInfo> list = page.getRecords();
        for (OrderInfo orderInfo : list) {
            this.setOrderOther(orderInfo);
        }
        return page;
    }
}
