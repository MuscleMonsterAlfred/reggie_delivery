package org.catarina.dto;

import lombok.Data;
import org.catarina.entity.Setmeal;
import org.catarina.entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
