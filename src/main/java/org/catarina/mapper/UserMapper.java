package org.catarina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.catarina.entity.Setmeal;
import org.catarina.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
