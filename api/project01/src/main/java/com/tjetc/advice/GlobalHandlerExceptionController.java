package com.tjetc.advice;

import com.tjetc.common.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类，用于处理所有Controller抛出的异常。
 */
@RestControllerAdvice
public class GlobalHandlerExceptionController {

    /**
     * 处理所有异常，包括Exception及其子类的异常。
     *
     * @param e 异常对象
     * @return JsonResult对象，表示异常信息
     */
    @ExceptionHandler({Exception.class})
    public JsonResult handlerAtiException(Exception e) {
        System.out.println("全局异常处理");
        e.printStackTrace();
        return new JsonResult<>(2, "出错，请联系管理员", null);
    }
}
