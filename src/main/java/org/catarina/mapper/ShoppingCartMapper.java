package org.catarina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.catarina.entity.ShoppingCart;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
