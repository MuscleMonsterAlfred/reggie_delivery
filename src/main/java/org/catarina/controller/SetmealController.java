package org.catarina.controller;

/**
 * @program: reggie_delivery
 * @ClassName SetmealController
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-24 02:27
 * @Version 1.0
 **/

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.catarina.common.Result;
import org.catarina.dto.DishDto;
import org.catarina.dto.SetmealDto;
import org.catarina.entity.Category;
import org.catarina.entity.Dish;
import org.catarina.entity.DishFlavor;
import org.catarina.entity.Setmeal;
import org.catarina.service.CategoryService;
import org.catarina.service.SetmealDishService;
import org.catarina.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐管理
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息：{}",setmealDto);
        setmealService.saveWithDish(setmealDto);
        return Result.success("新增套餐成功");
    }


    /**
     * 套餐分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name){
        //分页构造器对象
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);

        Page<SetmealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据name进行like模糊查询
        queryWrapper.like(name != null,Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageInfo,queryWrapper);

        //对象的拷贝
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");

        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //对象拷贝
            BeanUtils.copyProperties(item,setmealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            //根据分类id查询分类对象
            if(category != null){
                //分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);

        return Result.success(dtoPage);
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids){
        log.info("ids:{}",ids);
        setmealService.removeWithDish(ids);
        return Result.success("套餐删除成功！");
    }

    /**
     * 根据条件查询对应的菜品数据
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public Result<List<Setmeal>> list(Setmeal setmeal){

        //构造查询条件
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId()!=null, Setmeal::getCategoryId,setmeal.getCategoryId());
        //添加条件，查询状态为1（起售）的菜品
        queryWrapper.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());
        //添加排序条件
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(queryWrapper);

        return Result.success(list);
    }



}
