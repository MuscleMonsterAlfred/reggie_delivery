package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.catarina.entity.Dish;
import org.catarina.mapper.DishMapper;
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
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
