package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.Color;
import com.devvengers.mjoraste.entities.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsResponse {

    private Long id;


    private String name;


    private double price;


    private String description;


    private List<Color> colorOptions;

    private List<Size> sizeOptions;


    private int stock;


    private String img;


    private String brandName;


    private String categoryName;
}
