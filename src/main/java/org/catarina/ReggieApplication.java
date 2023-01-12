package org.catarina;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: reggie_delivery
 * @ClassName ReggieApplication
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-06 18:36
 * @Version 1.0
 **/
@Slf4j//打日志
@SpringBootApplication
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("项目启动成功......");
    }
}
