package com.devvengers.mjoraste.service.responses;

import com.devvengers.mjoraste.entities.Brand;
import com.devvengers.mjoraste.entities.Category;
import com.devvengers.mjoraste.entities.Color;
import com.devvengers.mjoraste.entities.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
