package com.hthyaq.malladmin;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static void main(String[] args) {
        String s = StringUtils.substringAfterLast("/image/item/47c2f40c37344cf0af30581bc034303e.jpg,/image/item/320a95000a4542b5a857fe9776a78c7d.jpg,/image/item/e6893a8b316f4919bb769a92e66bfb50.jpg,/image/item/0b1a570ad75d4f798ad12304f77ed01f.jpg", ",");
        String s1 = StringUtils.substringAfterLast("/image/item/30137d2dc81d464fbea424140d4053cd.jpg", ",");

        String str = "/image/item/30137d2dc81d464fbea424140d4053cd.jpg";

        System.out.println(s1);
    }
}
