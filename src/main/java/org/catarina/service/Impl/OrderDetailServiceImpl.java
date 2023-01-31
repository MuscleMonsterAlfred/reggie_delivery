package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.catarina.entity.OrderDetail;
import org.catarina.mapper.OrderDetailMapper;
import org.catarina.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @program: reggie_delivery
 * @ClassName OrderDetailServiceImpl
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-31 02:12
 * @Version 1.0
 **/
@Slf4j
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
