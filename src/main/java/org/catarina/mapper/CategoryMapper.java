package org.catarina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.catarina.entity.Category;
import org.catarina.entity.Employee;

@Mapper
public interface CategoryMapper  extends BaseMapper<Category> {

}
