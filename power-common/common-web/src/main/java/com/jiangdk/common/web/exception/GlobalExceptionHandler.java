package com.jiangdk.common.web.exception;

import cn.hutool.http.HttpStatus;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.common.result.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author: JiangDk
 * @date: 2024/11/29 12:33
 * @description: 统一处理controller层抛出的异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BindException.class)
    public Result processHandler(BindException e) {
        e.printStackTrace();
        String msg = e.getAllErrors().stream()
                .map(error-> error.getDefaultMessage())
                .collect(Collectors.joining(";"));
        return Result.error(HttpStatus.HTTP_BAD_REQUEST, msg);
    }
    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BizException.class)
    public Result processHandler(BizException e){
        e.printStackTrace();
        return Result.error(e.getCode(),e.getMessage());
    }
    /**
     * 其他业务异常处理
     */
    @ExceptionHandler(Throwable.class)
    public Result processHandler(Throwable e){
        e.printStackTrace();
        return Result.error(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
    }
}
