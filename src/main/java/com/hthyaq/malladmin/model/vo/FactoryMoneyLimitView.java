package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.bean.ChildForm;
import com.hthyaq.malladmin.model.entity.MoneyLimit;
import lombok.Data;

@Data
public class FactoryMoneyLimitView {
    private Integer companyId;
    private ChildForm<MoneyLimit> moneyLimits;
}
