package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.catarina.entity.Setmeal;
import org.catarina.entity.User;
import org.catarina.mapper.SetmealMapper;
import org.catarina.mapper.UserMapper;
import org.catarina.service.SetmealService;
import org.catarina.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @program: reggie_delivery
 * @ClassName UserServiceImpl
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-25 06:18
 * @Version 1.0
 **/

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
