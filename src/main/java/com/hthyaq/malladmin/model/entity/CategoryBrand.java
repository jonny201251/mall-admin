package com.hthyaq.malladmin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * category和brand的中间表
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CategoryBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * category表的id
     */
    private Integer categoryId;

    /**
     * brand表的id
     */
    private Integer brandId;


}
