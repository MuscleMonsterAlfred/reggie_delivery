package org.catarina.common;

/**
 * @program: reggie_delivery
 * @ClassName GlobalExceptionHandler
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-15 01:49
 * @Version 1.0
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
//拦截所有加了RestController和Controller注解的Controller类
@ControllerAdvice(annotations = {RestController.class, Controller.class})
//会将数据封装成JSON进行传输
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());

        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在当前账号";
            return Result.error(msg);
        }
        return Result.error("未知错误");
    }
}
