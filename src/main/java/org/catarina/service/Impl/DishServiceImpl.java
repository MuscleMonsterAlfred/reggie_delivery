package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.catarina.dto.DishDto;
import org.catarina.entity.Dish;
import org.catarina.entity.DishFlavor;
import org.catarina.mapper.DishMapper;
import org.catarina.service.DishFlavorService;
import org.catarina.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时保存对应的口味数据
     * @param dishDto
     */
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDto);
        //菜品id
        Long dishId = dishDto.getId();
        //菜品口味
        //讲道理这边推荐使用foreach,但是可以学一下stream流
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味数据到菜品口味表dishflavor
        dishFlavorService.saveBatch(flavors);
    }
}
