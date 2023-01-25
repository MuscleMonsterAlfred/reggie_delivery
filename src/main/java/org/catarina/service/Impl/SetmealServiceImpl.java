package org.catarina.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.catarina.common.CustomException;
import org.catarina.dto.SetmealDto;
import org.catarina.entity.Setmeal;
import org.catarina.entity.SetmealDish;
import org.catarina.mapper.SetmealMapper;
import org.catarina.service.SetmealDishService;
import org.catarina.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: reggie_delivery
 * @ClassName SetmealServiceImpl
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-18 02:58
 * @Version 1.0
 **/

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {


    @Autowired
    private SetmealDishService setmealDishService;


    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Override
    @Transactional//事务注解
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        //保存套餐和菜品的关联关系，操作setmenl_dish，执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }


    /**
     * 删除套餐
     * @param ids
     */
    @Override
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态，确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);

        int count = this.count(queryWrapper);
        if(count > 0){
            throw new CustomException("套餐正在售卖中，不能删除！");
        }
        //如果可以删除，先删除套餐表中的数据---setmeal
        this.removeByIds(ids);
        //删除关系表中的数据---setmeal_dish
        LambdaQueryWrapper<SetmealDish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        //删除关系表中的数据
        setmealDishService.remove(dishLambdaQueryWrapper);
    }
}
