package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.Address;
import com.devvengers.mjoraste.entities.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetAllOrdersResponse {

    private Long id;

    private Long userId;

    private List<OrderItem> orderItems;

    private String orderCode;

    private Boolean orderStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime orderDate;

    private Address shippingAddress;

    private Double totalPrice;

}
