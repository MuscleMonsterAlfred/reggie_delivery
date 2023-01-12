package org.catarina.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.catarina.entity.Employee;
import org.catarina.mapper.EmployeeMapper;
import org.catarina.service.EmployeeService;
import org.springframework.stereotype.Service;


/**
 * @program: reggie_delivery
 * @ClassName EmployeeServiceImpl
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-10 15:38
 * @Version 1.0
 **/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
