package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.entity.Company;
import lombok.Data;

@Data
public class AppSpuView {
    private Long spuId;
    private String image;
    private String title;
    private Double tmpPrice;
    private Company company;
}
