package com.devvengers.mjoraste.service.requests;

import lombok.Data;

@Data
public class CreateCartItemRequest {

    private Long productId;

    private int quantity;
}
