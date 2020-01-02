package com.hthyaq.malladmin.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//前端页面的子表的数据结构
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildForm<T> {
    private List<T> dataSource;
}
