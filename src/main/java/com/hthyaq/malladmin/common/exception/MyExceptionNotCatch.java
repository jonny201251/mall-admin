package com.hthyaq.malladmin.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

//发生异常时，首先GlobalExceptionHandler捕获，然后GlobalResponseResultAdvice处理，用于传递错误信息
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MyExceptionNotCatch extends RuntimeException{
    private String msg;
}
