package org.catarina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.catarina.entity.Dish;
import org.catarina.entity.DishFlavor;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
