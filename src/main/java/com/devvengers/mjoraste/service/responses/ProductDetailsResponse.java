package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.Brand;
import com.devvengers.mjoraste.entities.Category;
import lombok.Data;

@Data
public class ProductDetailsResponse {

    private Long id;


    private String name;


    private double price;


    private String description;


    private String size;


    private String color;


    private int stock;


    private String img;


    private Brand brandName;


    private Category categoryName;
}
