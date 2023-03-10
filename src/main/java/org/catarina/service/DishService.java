package org.catarina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.catarina.dto.DishDto;
import org.catarina.entity.Dish;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish，dishflavor
    @Transactional
    void saveWithFlavor(DishDto dishDto);

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    DishDto getByIdWithFlavor(Long id);

    //更新菜品，同时插入菜品对应的口味数据，需要操作两张表：dish，dishflavor
    @Transactional
    void updateWithFlavor(DishDto dishDto);
}
