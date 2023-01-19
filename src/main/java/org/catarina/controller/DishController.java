package org.catarina.controller;

/**
 * @program: reggie_delivery
 * @ClassName DishController
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-19 04:54
 * @Version 1.0
 **/

import lombok.extern.slf4j.Slf4j;
import org.catarina.common.Result;
import org.catarina.dto.DishDto;
import org.catarina.service.DishFlavorService;
import org.catarina.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 菜品管理
 */

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品
     * @param request
     * @param dishDto
     * @return
     */
    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody DishDto dishDto){
        log.info(dishDto.toString());

        dishService.saveWithFlavor(dishDto);

        return Result.success("新增菜品成功");
    }

}
