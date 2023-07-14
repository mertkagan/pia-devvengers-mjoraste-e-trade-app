package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.entities.Product;
import com.devvengers.mjoraste.service.concretes.ProductService;
import com.devvengers.mjoraste.service.requests.CreateProductRequest;
import com.devvengers.mjoraste.service.responses.GetAllProductResponse;
import com.devvengers.mjoraste.service.responses.ProductDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public DataResult<List<GetAllProductResponse>> getAllProducts(){
       return productService.getAllProductts();
    }

    @GetMapping("/details/{productId}")
    public DataResult<ProductDetailsResponse> getProductDetailsById(@PathVariable Long productId){
        return productService.getProductDetailsById(productId);
    }

    @GetMapping("/findByCategoryId/{categoryId}")
    public DataResult<List<GetAllProductResponse>>getAllProductsByCategoryId(@PathVariable Long categoryId){
        return productService.getAllProductsByCategoryId(categoryId);
    }

    @PostMapping
    public DataResult<Product> addProduct(@RequestParam CreateProductRequest createProductRequest){
        return productService.addProduct(createProductRequest);
    }








}
