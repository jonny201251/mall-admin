package com.hthyaq.malladmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 规格组下的规格参数
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpecificationParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * category表的id
     */
    private Integer categoryId;

    /**
     * specification_group表的id
     */
    private Integer groupId;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 是否是数字类型参数：0.不是，1.是
     */
    private Integer digit;

    /**
     * 数字类型参数的单位，非数字类型可以为空
     */
    private String unit;

    /**
     * 是否为通用属性：0.不是，1.是
     */
    private Integer generic;

    /**
     * 是否用于搜索过滤：0.否，1.是
     */
    private Integer searching;

    /**
     * 数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0
     */
    private String segments;

    /**
     * 排序
     */
    private Double sort;


}
