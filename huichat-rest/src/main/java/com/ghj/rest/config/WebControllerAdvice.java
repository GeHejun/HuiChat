package com.ghj.rest.config;

import com.ghj.common.base.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/10 14:28
 */
@ControllerAdvice
public class WebControllerAdvice {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
        return Result.defaultSuccess(ex.getMessage());
    }


}
