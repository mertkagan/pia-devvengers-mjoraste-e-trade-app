package com.devvengers.mjoraste.service.responses;

import lombok.Data;

import java.util.List;

@Data
public class GetUserCartResponse {

    private Long id;

    private Long userId;


    private List<CartItemResponse> cartItems;

    private Double totalPrice;
}
