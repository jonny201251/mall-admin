package com.hthyaq.malladmin.common.cron;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.model.entity.OrderStatus;
import com.hthyaq.malladmin.service.OrderStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//关闭订单的定时器
@EnableScheduling
@Component
@Slf4j
public class OrderStatusCron {
    @Autowired
    OrderStatusService orderStatusService;

    // 每隔10秒执行一次
//    @Scheduled(cron = "*/10 * * * * ?")
    //每天凌晨1点执行一次
    @Scheduled(cron = "0 0 1 * * ?")
    public void closeOrder() {
        //获取订单状态
        List<OrderStatus> list = orderStatusService.list(new QueryWrapper<OrderStatus>().eq("status", 0));
        //超过1个月的订单
        List<OrderStatus> list2 = Lists.newArrayList();
        //处理
        for (OrderStatus orderStatus : list) {
            LocalDateTime createTime = orderStatus.getCreateTime();
            LocalDateTime nowTime = LocalDateTime.now();
            LocalDateTime minusMonthTime = nowTime.minusMonths(1L);
            if (minusMonthTime.isAfter(createTime)) {
                orderStatus.setStatus(5);
                orderStatus.setCloseTime(nowTime);
                list2.add(orderStatus);
            }
        }
        if (list2.size() > 0) {
            List<String> tmp = list2.stream().map(OrderStatus::getOrderId).collect(Collectors.toList());
            log.info("订单号=" + Joiner.on(",").join(tmp) + ",更新时间=" + LocalDateTime.now());
            orderStatusService.updateBatchById(list2);
        }
    }
}
