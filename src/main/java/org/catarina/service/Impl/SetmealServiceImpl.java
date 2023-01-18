package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.catarina.entity.Setmeal;
import org.catarina.mapper.SetmealMapper;
import org.catarina.service.SetmealService;
import org.springframework.stereotype.Service;

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
}
