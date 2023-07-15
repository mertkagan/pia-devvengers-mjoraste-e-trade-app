package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.ErrorDataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.Brand;
import com.devvengers.mjoraste.entities.Category;
import com.devvengers.mjoraste.entities.Color;
import com.devvengers.mjoraste.entities.Product;
import com.devvengers.mjoraste.repository.ProductRepository;
import com.devvengers.mjoraste.service.requests.CreateProductRequest;
import com.devvengers.mjoraste.service.responses.GetAllProductByCategoryIdResponse;
import com.devvengers.mjoraste.service.responses.GetAllProductResponse;
import com.devvengers.mjoraste.service.responses.ProductDetailsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private ModelMapperService modelMapperService;

    private BrandService brandService;

    private CategoryService categoryService;

    private ColorService colorService;

    public DataResult<List<GetAllProductResponse>> getAllProducts(){

        List<Product> products = productRepository.findAll();

        List<GetAllProductResponse> response = new ArrayList<>();

        for (Product product : products) {
            GetAllProductResponse productResponse = this.modelMapperService.forResponse().map(product, GetAllProductResponse.class);
            response.add(productResponse);
        }

        return new SuccessDataResult<List<GetAllProductResponse>>
                (response,"Products retrieved successfully.");
    }

    public DataResult<ProductDetailsResponse> getProductDetailsById(Long productId){

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null ) {
            return new ErrorDataResult<>(null, "Category not found with the given ID.");
        }else {
            ProductDetailsResponse response = this.modelMapperService.forResponse().map(product, ProductDetailsResponse.class);
            return new SuccessDataResult<ProductDetailsResponse>(response, "Product details retrieved successfully.");
        }


    }

    public DataResult<List<GetAllProductByCategoryIdResponse>> getAllProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);

        if (products.isEmpty()) {
            return new ErrorDataResult<>(null, "Category not found with the given ID.");
        }

        List<GetAllProductByCategoryIdResponse> response = new ArrayList<>();
        for (Product product : products) {
            GetAllProductByCategoryIdResponse productResponse = this.modelMapperService.forResponse().map(product, GetAllProductByCategoryIdResponse.class);
            response.add(productResponse);
        }

        return new SuccessDataResult<List<GetAllProductByCategoryIdResponse>>(response, "Data retrieved successfully.");
    }



    public DataResult<Product> addProduct(CreateProductRequest createProductRequest) {
        Optional<Brand> findBrandById = brandService.getBrandById(createProductRequest.getBrandId());
        Optional<Category> findCategoryById = categoryService.getCategoryById(createProductRequest.getCategoryId());
        if (!findBrandById.isPresent()) {
            return new ErrorDataResult<>(null, "Brand not found with the given ID.");
        }

        if (!findCategoryById.isPresent()) {
            return new ErrorDataResult<>(null, "Category not found with the given ID.");
        }

        if (!findCategoryById.isPresent()) {
            return new ErrorDataResult<>(null, "Category not found with the given ID.");
        }


        Product newProduct = this.modelMapperService.forRequest().map(createProductRequest, Product.class);
        productRepository.save(newProduct);

        return new SuccessDataResult<>(newProduct, "The product has been successfully added.");
    }



}
