package org.catarina.common;

/**
 * @program: reggie_delivery
 * @ClassName CustomException
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-18 03:10
 * @Version 1.0
 **/

/**
 * 自定义业务异常
 */
public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }
}
