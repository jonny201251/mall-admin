package com.hthyaq.malladmin.common.annotation;

import java.lang.annotation.*;

//用来标记方法的返回值，是否需要包装
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}
