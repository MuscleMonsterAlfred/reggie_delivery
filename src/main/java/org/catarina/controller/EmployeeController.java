package org.catarina.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.catarina.common.Result;
import org.catarina.entity.Employee;
import org.catarina.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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
     * 其实推荐放到Service层去
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
        if(!emp.getPassword().equals(password)){
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


    /**
     * 用户退出当前账号
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest httpServletRequest){
        //清理session中保存的当前登录员工的id
        httpServletRequest.getSession().removeAttribute("employee");
        return Result.success("退出成功！");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public Result<String> save(HttpServletRequest httpServletRequest,@RequestBody Employee employee){
        log.info("新增员工，员工信息:{}", employee.toString());
//        try{
            //设置初始密码，但需要进行md5加密
            employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            //创建时间和更新时间默认当前
            employee.setCreateTime(LocalDateTime.now());
            employee.setUpdateTime(LocalDateTime.now());
            //获取当前登录用户的id
            Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
            employee.setCreateUser(empId);
            employee.setUpdateUser(empId);
            employeeService.save(employee);
            log.info("新增员工成功！");

//        }catch (Exception e){
//            return Result.error("新增员工失败，请检查信息是否正确...");
//        }
        return Result.success("新增员工成功！");
    }

    /**
     * 员工信息的分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<Page> pageList(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }


}
