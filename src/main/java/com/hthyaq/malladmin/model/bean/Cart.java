package com.hthyaq.malladmin.model.bean;

import lombok.Data;

@Data
public class Cart {
    private Long skuId;// 商品id
    private String title;// 标题
    private String image;// 图片
    private Double price;// 加入购物车时的价格
    private Integer num;// 购买数量
    private String skuSpec;// 商品规格参数,用于特有属性，通用属性的商品不用
}
