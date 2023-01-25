package org.catarina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.catarina.dto.SetmealDto;
import org.catarina.entity.Setmeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐
     * @param ids
     */
    @Transactional
    void removeWithDish(List<Long> ids);
}
