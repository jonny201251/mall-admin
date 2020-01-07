package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.bean.ChildForm;
import com.hthyaq.malladmin.model.entity.Sku;
import com.hthyaq.malladmin.model.entity.SpecSellerDefine;
import com.hthyaq.malladmin.model.entity.Spu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
//反显商品数据
public class SpuReverseView extends Spu {
    //spu_detail
    private String description;
    private String genericSpec;
    private String specialSpec;
    private String packingList;
    private String afterService;
    //speSellerDefine
    private ChildForm<SpecSellerDefine> specSellerDefine;
    //规格模板
    private String type;
    //库存商品的sku数据
    private List<Sku> skuList;
}
