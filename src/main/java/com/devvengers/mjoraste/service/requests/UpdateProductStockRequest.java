package com.devvengers.mjoraste.service.requests;

import lombok.Data;

@Data
public class UpdateProductStockRequest {

    private Long productId;

    private int stock;
}
