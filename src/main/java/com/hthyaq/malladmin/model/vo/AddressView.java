package com.hthyaq.malladmin.model.vo;

import lombok.Data;

@Data
public class AddressView {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Integer isDefault;
}
