package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.catarina.entity.Dish;
import org.catarina.entity.DishFlavor;
import org.catarina.mapper.DishFlavorMapper;
import org.catarina.mapper.DishMapper;
import org.catarina.service.DishFlavorService;
import org.catarina.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @program: reggie_delivery
 * @ClassName DishServiceImpl
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-18 02:56
 * @Version 1.0
 **/

@Service
@Slf4j
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
