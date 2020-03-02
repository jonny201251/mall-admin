package com.hthyaq.malladmin.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
@Data
public class SearchItems {
    private Long id; // spuId
    private String title; // 所有需要被搜索的信息，包含标题，分类，甚至品牌
    private String subTitle;// 卖点
    private Integer brandId;// 品牌id
    private Integer cid1;// 1级分类id
    private Integer cid2;// 2级分类id
    private Integer cid3;// 3级分类id
    private LocalDateTime createTime;// 创建时间
    private Set<Double> price;// 价格，一个spu有多个sku，就有多个价格
    private String skus;// sku信息的json结构，只是一个展示结果
    private Map<String, Object> specs;// 可搜索的规格参数，key是参数名，值是参数值
}
