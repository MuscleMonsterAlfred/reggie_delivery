package org.catarina.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.authenticator.DigestAuthenticator;
import org.catarina.common.Result;
import org.catarina.entity.Employee;
import org.catarina.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: reggie_delivery
 * @ClassName EmployeeController
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-10 15:40
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 用户登录功能
     * @param httpServletRequest
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest httpServletRequest, @RequestBody Employee employee){
        //将页面提交的密码进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(lambdaQueryWrapper);
        //如果没有查询到就返回登录失败
        if(emp == null){
            return Result.error("登录失败！");
        }
        //密码不一致，返回登录失败
        if(emp.getPassword().equals(password)){
            return Result.error("登录失败！请检查用户名和密码...");
        }
        //查看员工状态，被锁定状态返回登录失败
        if(emp.getStatus() == 0){
            return Result.error("该员工账号已被禁用！");
        }

        //登录成功，将员工id存入session并返回登录成功
        httpServletRequest.getSession().setAttribute("employee",emp.getId());
        return Result.success(emp);
    }

}
