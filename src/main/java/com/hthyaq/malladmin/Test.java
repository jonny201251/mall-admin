package com.hthyaq.malladmin;

import cn.hutool.core.util.IdUtil;
import com.hthyaq.malladmin.common.utils.GenerateOrderId;

public class Test {
    public static void main(String[] args) {
        System.out.println(new GenerateOrderId().nextId());
        System.out.println(IdUtil.getSnowflake(1,1).nextId());
    }
}
