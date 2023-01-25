package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.catarina.dto.SetmealDto;
import org.catarina.entity.Setmeal;
import org.catarina.entity.SetmealDish;
import org.catarina.mapper.SetmealDishMapper;
import org.catarina.mapper.SetmealMapper;
import org.catarina.service.SetmealDishService;
import org.catarina.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {


}
