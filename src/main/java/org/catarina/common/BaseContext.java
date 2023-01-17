package org.catarina.common;

/**
 * @program: reggie_delivery
 * @ClassName BaseContext
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-17 06:13
 * @Version 1.0
 **/

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
