package com.devvengers.mjoraste.service.responses;

import lombok.Data;

import java.util.List;

@Data
public class GetProductsBySearchResponse {

    private Long id;


    private String name;


    private double price;


    private List<GetProductImageResponse> images;


    private String brandName;


}
