package com.tjetc.advice;

import com.tjetc.common.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//局部异常处理（对指定相同的异常）局部优先级高，全局优先级低，可以把特有处理逻辑放在局部异常处理 公共处理逻辑放到全局异常处理
//全局处理异常 对所有的controller的制定异常都会捕获与处理
//@ControllerAdvice+@ResponseBody
@RestControllerAdvice
public class GlobalHandlerExceptionController {
    //    处理异常上 使用Exception 这样包含常用的所有异常情况
//    @ExceptionHandler({ArithmeticException.class, NullPointerException.class, Exception.class})
    @ExceptionHandler({Exception.class})
    public JsonResult handlerAtiException(Exception e) {
        System.out.println("全局异常处理");
        e.printStackTrace();
        return new JsonResult<>(2, "出错，请联系管理员", null);
    }
}
