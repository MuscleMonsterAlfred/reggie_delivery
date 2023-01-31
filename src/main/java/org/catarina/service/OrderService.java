package org.catarina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.catarina.entity.Orders;

public interface OrderService extends IService<Orders> {


    /**
     * 用户提交订单
     * @param orders
     */
    void submit(Orders orders);
}
