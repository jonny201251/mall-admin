package com.hthyaq.malladmin.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

//发生异常时，首先GlobalExceptionHandler捕获，然后GlobalResponseResultAdvice处理，用于传递错误信息
@Data
@AllArgsConstructor
public class MyExceptionNotCatch extends RuntimeException{
    private String msg;
}
