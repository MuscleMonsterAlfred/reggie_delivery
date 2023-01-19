package org.catarina.dto;

import lombok.Data;
import org.catarina.entity.Dish;
import org.catarina.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
