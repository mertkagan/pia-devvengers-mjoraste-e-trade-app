package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class GetUserOrderResponse {

    private Long id;

    private Long userId;

    private List<OrderItem> orderItems;

    private Double totalPrice;
}
