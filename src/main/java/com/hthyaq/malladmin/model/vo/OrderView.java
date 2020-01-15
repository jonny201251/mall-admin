package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.entity.Order;
import com.hthyaq.malladmin.model.entity.OrderDetail;
import com.hthyaq.malladmin.model.entity.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderView extends Order {
    private List<OrderDetail> orderDetails;
    private OrderStatus orderStatus;
}
