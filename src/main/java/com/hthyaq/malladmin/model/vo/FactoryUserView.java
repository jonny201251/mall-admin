package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.bean.ChildForm;
import com.hthyaq.malladmin.model.entity.SysUser;
import lombok.Data;

@Data
public class FactoryUserView {
    private Integer companyId;
    private ChildForm<SysUser> users;
}
