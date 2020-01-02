package com.hthyaq.malladmin;

import cn.hutool.core.util.ArrayUtil;

public class Test {
    public static void main(String[] args) {
        String[] str = "1,2,3".split(",");
        Integer[] categoryArr = ArrayUtil.newArray(Integer.class,str.length);
        System.out.println(categoryArr.toString());
        System.out.println(categoryArr.toString());
        System.out.println(categoryArr.toString());
    }
}
