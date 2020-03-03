package com.hthyaq.malladmin;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static void main(String[] args) {
       String s= StringUtils.substringAfter("aa.jpg,b.png", ",");
        System.out.println(s);
    }
}
