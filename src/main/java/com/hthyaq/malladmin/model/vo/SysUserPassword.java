package com.hthyaq.malladmin.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysUserPassword {
    private String loginPassword;
    private String newPassword;
}
