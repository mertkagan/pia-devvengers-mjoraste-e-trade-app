package com.devvengers.mjoraste.service.requests;

import lombok.Data;

@Data
public class CreateProductRequest {

    private String name;
    private double price;
    private String description;
    private String size;
    private String color;
    private int stock;
    private String img;
    private Long brandId;
    private Long categoryId;
}
