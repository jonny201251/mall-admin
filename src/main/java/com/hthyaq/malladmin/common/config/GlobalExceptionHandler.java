package com.hthyaq.malladmin.common.config;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.hthyaq.malladmin.common.exception.MyException;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public MyException handleException(HttpServletRequest request, Exception e) {
        try {
            MyExceptionNotCatch myExceptionNotCatch = (MyExceptionNotCatch) e;
            if (e != null) {
                log.error(e.getMessage().length() > 50 ? "java代码错误!" : e.getMessage());
                throw myExceptionNotCatch;
            }
        } catch (Exception e2) {
        }
        //记录异常信息到日志
        log.error("url={}", request.getRequestURL());
        log.error("method={}", request.getMethod());
        log.error("params={}", JSON.toJSONString(request.getParameterMap()));
        log.error("error={}", Throwables.getStackTraceAsString(e));
        return new MyException(e.getMessage().length() > 50 ? "java代码错误!" : e.getMessage());
    }
}
