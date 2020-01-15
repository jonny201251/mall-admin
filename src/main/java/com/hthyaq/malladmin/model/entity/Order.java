package com.hthyaq.malladmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单表，包括用户信息、收货地址
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-15
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    private Long orderId;

    /**
     * 总金额
     */
    private Double totalPay;

    /**
     * 实付金额。
     */
    private Double actualPay;
    //参与促销活动的id
    private String promotionIds;

    /**
     * 支付类型，0、暂不付款 1、在线支付，2、货到付款
     */
    private Integer paymentType;

    /**
     * 邮费
     */
    private Double postFee;

    /**
     * 订单创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 物流单号
     */
    private String shippingCode;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家昵称
     */
    private String buyerNick;

    /**
     * 买家是否已经评价,0未评价，1已评价
     */
    private Integer buyerRate;

    /**
     * 收获地址（省）
     */
    private String receiverState;

    /**
     * 收获地址（市）
     */
    private String receiverCity;

    /**
     * 收获地址（区/县）
     */
    private String receiverDistrict;

    /**
     * 收获地址（街道、住址等详细地址）
     */
    private String receiverAddress;

    /**
     * 收货人手机
     */
    private String receiverMobile;

    /**
     * 收货人邮编
     */
    private String receiverZip;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 发票类型(0无发票1普通发票，2电子发票，3增值税发票)
     */
    private Integer invoiceType;

    /**
     * 订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端
     */
    private Integer sourceType;

    @TableField(exist = false)
    private List<OrderDetail> orderDetails;

    @TableField(exist = false)
    private OrderStatus orderStatus;
}
