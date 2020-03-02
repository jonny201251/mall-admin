package com.hthyaq.malladmin;

import org.apache.commons.lang.StringUtils;

public class Test {
    public static void main(String[] args) {
        System.out.println(StringUtils.substringBefore("http://localhost:8082/mall/image/item/b2c4a3c0b855473180199ebb7b2a5f8b.5 100,http://localhost:8082/mall/image/item/f8140711d6ac4ea4bb78a725a2a83a99.5 100图片2,http://localhost:8082/mall/image/item/f9cb936f31cb40439de0e854c82a2df8.5 100图片3",",") );
    }
}
