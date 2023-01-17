package org.catarina.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: reggie_delivery
 * @ClassName MyMetaObjectHandler
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-17 05:48
 * @Version 1.0
 **/

/**
 * 自定义元数据对象处理器
 */

/**
 *
 * 使用ThreadLocal之前须知：
 * 在一次http请求的时候，对应的服务器都会给分配一个新的线程来处理
 * 在处理过程中涉及到下面类中的方法都属于相同的一个线程
 * 1.LoginCheckFilter  doFilter
 * 2.EmployeeController  update
 * 3.MyMetaObjectHandler   updateFill
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入操作，自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }


    /**
     * 更新操作，自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);

        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}
