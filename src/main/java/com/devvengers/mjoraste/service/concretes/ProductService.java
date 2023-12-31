package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.*;
import com.devvengers.mjoraste.entities.*;
import com.devvengers.mjoraste.repository.ProductRepository;
import com.devvengers.mjoraste.service.requests.CreateProductImageRequest;
import com.devvengers.mjoraste.service.requests.CreateProductRequest;
import com.devvengers.mjoraste.service.requests.UpdateProductStockRequest;
import com.devvengers.mjoraste.service.responses.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    private ProductImageService productImageService;


    public DataResult<List<GetAllProductResponse>> getAllProducts(){

        List<Product> products = productRepository.findAll();
        List<GetAllProductResponse> response = new ArrayList<>();

        for (Product product : products) {
            GetAllProductResponse productResponse = new GetAllProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setBrandName(product.getBrand().getName());

            List<GetProductImageResponse> imageResponses = new ArrayList<>();
            for (ProductImage image : product.getImages()) {
                GetProductImageResponse imageResponse = new GetProductImageResponse();
                imageResponse.setImageUrl(image.getImageUrl());
                imageResponses.add(imageResponse);
            }

            productResponse.setImages(imageResponses);
            response.add(productResponse);
        }

        return new SuccessDataResult<>(response, "Products retrieved successfully.");
    }

    public DataResult<ProductDetailsResponse> getProductDetailsById(Long productId){

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null ) {
            return new ErrorDataResult<>(null, "Product not found with the given ID.");
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



    public Result addProduct(CreateProductRequest createProductRequest) {
        Optional<Category> category = categoryService.getCategoryById(createProductRequest.getCategoryId());
        Optional<Brand> brand = brandService.getBrandById(createProductRequest.getBrandId());

        if (category == null) {
            return new ErrorResult("Invalid category");
        } else if (brand == null) {
            return new ErrorResult("Invalid brand");
        } else {
            Product product = this.modelMapperService.forRequest().map(createProductRequest, Product.class);
            product.setImages(new ArrayList<>());

            for (CreateProductImageRequest imageRequest : createProductRequest.getImageList()) {
                ProductImage image = new ProductImage();
                image.setImageUrl(imageRequest.getImageUrl());
                image.setProduct(product);
                product.getImages().add(image);
            }
            productRepository.save(product);
            return new SuccessResult("Product created successfully");
        }


    }

    public DataResult<List<GetProductsBySearchResponse>> getProductsBySearch(String productName){

        List<Product> products = productRepository.findByNameContainingIgnoreCase(productName);


        List<GetProductsBySearchResponse> responses = products.stream()
                .map(product -> this.modelMapperService.forResponse().map(product, GetProductsBySearchResponse.class))
                .collect(Collectors.toList());

        if (products!= null){
            return new SuccessDataResult<List<GetProductsBySearchResponse>>(responses,"Data retrieved succesfully");
        }else {
            return new ErrorDataResult<>(null,"The product you were looking for was not found");
        }
    }

    public Result deleteProductByProductId(Long productId){

        Product product = productRepository.findById(productId).orElse(null);
        if(product == null) {
            return new ErrorResult("Product not found.");
        }

        productRepository.deleteById(productId);
        return new SuccessResult("Product deleted succesfully");
    }

    public Result updateProductStockByProductId(UpdateProductStockRequest updateProductStockRequest) {
        Product product = productRepository.findById(updateProductStockRequest.getProductId()).orElse(null);

        if (product == null){
            return new ErrorResult("Product not found");
        }

        product.setStock(updateProductStockRequest.getStock());
        productRepository.save(product);

        return new SuccessResult("Product stock updated succesfully.");
    }
}




