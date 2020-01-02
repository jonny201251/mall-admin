package com.hthyaq.malladmin.common.utils.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeDto {
    private Integer id;
    private Integer pid;
    private String name;
}
