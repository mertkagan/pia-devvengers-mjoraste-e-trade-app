package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.entities.Product;
import com.devvengers.mjoraste.service.concretes.ProductService;
import com.devvengers.mjoraste.service.requests.CreateProductRequest;
import com.devvengers.mjoraste.service.responses.GetAllProductByCategoryIdResponse;
import com.devvengers.mjoraste.service.responses.GetAllProductResponse;
import com.devvengers.mjoraste.service.responses.ProductDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products") //localhost:1907/api/products
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public DataResult<List<GetAllProductResponse>> getAllProducts(){
       return productService.getAllProducts();
    }

    @GetMapping("/details/{productId}")
    public DataResult<ProductDetailsResponse> getProductDetailsById(@PathVariable Long productId){
        return productService.getProductDetailsById(productId);
    }
    

    @GetMapping("/findByCategoryId/{categoryId}")
    public DataResult<List<GetAllProductByCategoryIdResponse>>getAllProductsByCategoryId(@PathVariable Long categoryId){
        return productService.getAllProductsByCategoryId(categoryId);
    }



    @PostMapping
    @Validated
    public DataResult<Product> addProduct(@Valid @RequestBody CreateProductRequest createProductRequest){
        return productService.addProduct(createProductRequest);
    }








}
