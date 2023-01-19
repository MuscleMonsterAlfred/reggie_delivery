package org.catarina.dto;

import lombok.Data;
import org.catarina.entity.OrderDetail;
import org.catarina.entity.Orders;

import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
