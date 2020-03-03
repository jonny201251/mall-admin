package com.hthyaq.malladmin.common.utils;

import org.apache.commons.lang3.StringUtils;

public class StringLastUtil {
    //获取最后一个字符串
    public static String get(String str) {
        String result = null;
        String[] strArr = str.split(",");
        if (strArr.length == 1) {
            result = str;
        } else {
            result = StringUtils.substringAfterLast(str, ",");
        }
        return result;
    }
}
