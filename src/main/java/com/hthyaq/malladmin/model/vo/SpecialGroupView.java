package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.model.entity.SpecificationGroup;
import com.hthyaq.malladmin.model.entity.SpecificationParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpecialGroupView extends SpecificationGroup {
    List<SpecificationParam> params;// 该组下的所有规格参数集合
}
