package com.eloim.servicebase.handler;

import com.eloim.commonutils.Result;

import com.eloim.servicebase.entity.GuLiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    //指定出现什么异常会执行这个方法
    @ExceptionHandler(Exception.class)
    //因为他不在Controller中。没有@RestController，所以数据不会返回，需要加@ResponeseBody返回数据
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理。。。");
    }
    @ExceptionHandler(GuLiException.class)
    @ResponseBody
    public Result error(GuLiException e){
        e.printStackTrace();
        return Result.error().message(e.getMsg()).code(e.getCode());
    }

}
