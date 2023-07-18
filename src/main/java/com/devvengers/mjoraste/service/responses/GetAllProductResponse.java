package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductResponse {

    private Long id;


    private String name;


    private double price;


    private List<GetProductImageResponse> images;


    private String brandName;



}
