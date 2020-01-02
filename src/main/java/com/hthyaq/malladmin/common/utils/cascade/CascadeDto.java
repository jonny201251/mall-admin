package com.hthyaq.malladmin.common.utils.cascade;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CascadeDto {
    private Integer id;
    private Integer pid;
    private String name;
}
