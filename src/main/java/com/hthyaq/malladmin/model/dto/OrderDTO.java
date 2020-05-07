package com.hthyaq.malladmin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long addressId; // 收获人地址id

    private Integer paymentType;// 付款类型

    private List<CartDTO> carts;// 订单详情

    private Integer companyId;//商家id

    private String taskNum;//任务号
}