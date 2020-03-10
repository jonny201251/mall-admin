package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.entity.Brand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BrandView extends Brand {
    private Integer[] categoryArr;
}
