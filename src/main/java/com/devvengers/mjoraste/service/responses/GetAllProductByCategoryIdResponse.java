package com.devvengers.mjoraste.service.responses;

import lombok.Data;

@Data
public class GetAllProductByCategoryIdResponse {

    private Long id;


    private String name;


    private double price;


    private String img;

    private String categoryName;


    private String brandName;

}
