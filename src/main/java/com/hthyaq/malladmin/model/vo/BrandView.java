package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.entity.Brand;
import lombok.Data;

@Data
public class BrandView extends Brand {
    private Integer[] categoryArr;
}
