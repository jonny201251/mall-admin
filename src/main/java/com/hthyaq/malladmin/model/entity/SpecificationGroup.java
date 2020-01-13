package com.hthyaq.malladmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 规格参数的分组表、规格参数组、规格组
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpecificationGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * category表的id
     */
    private Integer categoryId;

    /**
     * 规格组的名称
     */
    private String name;

    /**
     * 排序
     */
    private Double sort;

}
