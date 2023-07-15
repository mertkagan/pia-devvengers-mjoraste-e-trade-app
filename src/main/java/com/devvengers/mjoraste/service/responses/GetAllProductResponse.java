package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.Brand;
import com.devvengers.mjoraste.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductResponse {

    private Long id;


    private String name;


    private double price;


    private String img;


    private String brandName;

}
