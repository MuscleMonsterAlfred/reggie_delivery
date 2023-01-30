package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.catarina.entity.ShoppingCart;
import org.catarina.mapper.ShoppingCartMapper;
import org.catarina.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @program: reggie_delivery
 * @ClassName ShoppingCartServiceImpl
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-30 18:52
 * @Version 1.0
 **/

@Service
@Slf4j
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
