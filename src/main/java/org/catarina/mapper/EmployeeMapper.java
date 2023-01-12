package org.catarina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.catarina.entity.Employee;

/**
 * @program: reggie_delivery
 * @ClassName EmployeeMapper
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-07 01:49
 * @Version 1.0
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
