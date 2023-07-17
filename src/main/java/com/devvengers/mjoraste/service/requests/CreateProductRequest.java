package com.devvengers.mjoraste.service.requests;

import com.devvengers.mjoraste.entities.Color;
import com.devvengers.mjoraste.entities.Size;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateProductRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Price is mandatory")
    private Double price;

    @NotBlank(message = "Description is mandatory")
    private String description;


    private List<CreateProductColorRequest> colorOptions;

    private List<CreateProductSizeRequest> sizeOptions;


    @NotNull(message = "Stock is mandatory")
    private Integer stock;

    @NotBlank(message = "Image URL is mandatory")
    private String img;

    @NotNull(message = "Brand ID is mandatory")
    private Long brandId;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;
}
