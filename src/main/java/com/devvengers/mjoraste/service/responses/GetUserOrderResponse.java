package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.Address;
import com.devvengers.mjoraste.entities.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetUserOrderResponse {

    private Long id;

    private String orderCode;

    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime orderDate;

    private List<OrderItem> orderItems; //

    private Address shippingAddress;

    private Double totalPrice;

    private Boolean orderStatus;
}
