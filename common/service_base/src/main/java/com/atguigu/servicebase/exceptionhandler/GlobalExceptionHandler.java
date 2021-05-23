package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//所有异常都使用此方法
    @ResponseBody//为了能够返回数据
    public R error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("执行了全局异常处理。。。");
    }
    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)//ArithmeticException异常都使用此方法
    @ResponseBody//为了能够返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("执行了全ArithmeticException异常处理方法。。。");
    }

    //自定义异常处理
    @ExceptionHandler(GuliException.class)//GuliException异常都使用此方法
    @ResponseBody//为了能够返回数据
    public R error(GuliException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().code(e.getCode()).message(e.getMsg());

    }
}
