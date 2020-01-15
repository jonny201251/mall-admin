package com.hthyaq.malladmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单状态表
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    private Long orderId;

    /**
     * 状态：0、暂不付款 1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功 5、交易关闭 6、已评价
     */
    private Integer status;

    /**
     * 订单创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime consignTime;

    /**
     * 交易完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 交易关闭时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closeTime;

    /**
     * 评价时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentTime;


}
