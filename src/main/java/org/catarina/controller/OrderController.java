package org.catarina.controller;

import lombok.extern.slf4j.Slf4j;
import org.catarina.common.Result;
import org.catarina.entity.Orders;
import org.catarina.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: reggie_delivery
 * @ClassName OrderController
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-31 02:13
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return Result.success("已提交订单");
    }
}