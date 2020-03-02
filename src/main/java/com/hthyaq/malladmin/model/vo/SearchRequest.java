package com.hthyaq.malladmin.model.vo;

import lombok.Data;

import java.util.Map;

@Data
public class SearchRequest {
    private static final Integer DEFAULT_PAGE = 1;
    private static final Integer DEFAULT_SIZE = 20;
    private String key;//搜索条件
    private Integer page;//当前页
    private Integer size = 20;//页面大小
    private String sortBy;//排序字段
    private Boolean descending; //是否降序
    private Map<String, String> filter;//过滤字段

    public Integer getPage() {
        if (page == null) {//默认为1
            return DEFAULT_PAGE;
        }
        // 获取页码时做一些校验，不能小于1
        return Math.max(DEFAULT_PAGE, page);
    }

}