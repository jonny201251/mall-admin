package com.hthyaq.malladmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收货地址
 * </p>
 *
 * @author zhangqiang
 * @since 2020-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReceiveAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户的真实姓名
     */
    private String realName;

    /**
     * 用户的手机号
     */
    private String mobile;

    /**
     * 收获地址（省）
     */
    private String state;

    /**
     * 收获地址（市）
     */
    private String city;

    /**
     * 收获地址（区/县）
     */
    private String district;

    /**
     * 收获地址（街道、住址等详细地址）
     */
    private String address;

    /**
     * zipcode,收货人邮编
     */
    private String zip;

    /**
     * 默认地址，0：不是默认，1：默认
     */
    @TableField("isDefault")
    private Boolean isDefault;

    /**
     * sys_user表的id
     */
    private Integer userId;

    /**
     * 商家、公司的id
     */
    private Integer companyId;


}
