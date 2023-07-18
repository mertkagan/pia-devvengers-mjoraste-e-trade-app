package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.ProductImage;
import lombok.Data;

import java.util.List;

@Data
public class GetAllProductByCategoryIdResponse {

    private Long id;


    private String name;


    private double price;


    private List<GetProductImageResponse> images;

    private String categoryName;


    private String brandName;

}
