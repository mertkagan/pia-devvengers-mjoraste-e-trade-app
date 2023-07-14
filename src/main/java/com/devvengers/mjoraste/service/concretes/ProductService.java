package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.ErrorDataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.Brand;
import com.devvengers.mjoraste.entities.Product;
import com.devvengers.mjoraste.repository.ProductRepository;
import com.devvengers.mjoraste.service.requests.CreateProductRequest;
import com.devvengers.mjoraste.service.responses.GetAllProductResponse;
import com.devvengers.mjoraste.service.responses.ProductDetailsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public DataResult<List<GetAllProductResponse>> getAllProductts(){

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

        if (product != null) {
            ProductDetailsResponse response = this.modelMapperService.forResponse().map(product, ProductDetailsResponse.class);
            return new SuccessDataResult<ProductDetailsResponse>(response, "Product details retrieved successfully.");
        } else {
            return new ErrorDataResult<>(null, "Product not found with the given ID.");
        }
    }

    public DataResult<List<GetAllProductResponse>> getAllProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);

        if (products != null){
            List<GetAllProductResponse> response = new ArrayList<>();
            for (Product product : products) {
                GetAllProductResponse productResponse = this.modelMapperService.forResponse().map(product, GetAllProductResponse.class);
                response.add(productResponse);
            }
            return new SuccessDataResult<List<GetAllProductResponse>>
                    (response,"Data retrieved succesfully.");
        }else {
            return new ErrorDataResult<>(null, "Category not found with the given ID.");
        }
    }

    public DataResult<Product> addProduct(CreateProductRequest createProductRequest) {
            return null;
    }
}
