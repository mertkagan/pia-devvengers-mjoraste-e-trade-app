package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.ProductImage;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsResponse {

    private Long id;


    private String name;


    private double price;


    private String description;


    private String colorName;

    private String size;


    private int stock;


    private List<GetProductImageResponse> images;


    private String brandName;


    private String categoryName;
}
