package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.entity.Brand;
import com.hthyaq.malladmin.model.entity.Category;

import java.util.List;
import java.util.Map;

public class SearchResult {
    private Long total;//总条数
    private Integer totalPage;//总页数
    private List<SearchItems> items;
    private List<Category> categories;// 分类过滤条件
    private List<Brand> brands; // 品牌过滤条件
    private List<Map<String, Object>> specs; // 规格参数过滤条件
}
