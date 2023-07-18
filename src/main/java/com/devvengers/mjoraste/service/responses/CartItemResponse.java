package com.devvengers.mjoraste.service.responses;

import lombok.Data;

import java.util.List;

@Data
public class CartItemResponse {

    private Long id;

    private String name;

    private String categoryName;

    private Double price;

    private int quantity;

    private Double totalItemPrice;

   private List<GetProductImageResponse> images;
}
