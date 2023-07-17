package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.ErrorDataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.*;
import com.devvengers.mjoraste.repository.ProductRepository;
import com.devvengers.mjoraste.service.requests.CreateProductColorRequest;
import com.devvengers.mjoraste.service.requests.CreateProductRequest;
import com.devvengers.mjoraste.service.requests.CreateProductSizeRequest;
import com.devvengers.mjoraste.service.responses.GetAllProductByCategoryIdResponse;
import com.devvengers.mjoraste.service.responses.GetAllProductResponse;
import com.devvengers.mjoraste.service.responses.ProductDetailsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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



    public DataResult<Product> addProduct(CreateProductRequest createProductRequest) {
        Brand brand = brandService.getBrandById(createProductRequest.getBrandId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found with ID: " + createProductRequest.getBrandId()));
        Category category = categoryService.getCategoryById(createProductRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + createProductRequest.getCategoryId()));

        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        product.setDescription(createProductRequest.getDescription());
        product.setStock(createProductRequest.getStock());
        product.setImg(createProductRequest.getImg());
        product.setBrand(brand);
        product.setCategory(category);
        product.setColorOptions(new ArrayList<>());
        product.setSizeOptions(new ArrayList<>());

        // Ürün renk seçeneklerini oluşturup ürüne eklemek için döngü
        for (CreateProductColorRequest colorRequest : createProductRequest.getColorOptions()) {
            Color color = new Color();
            color.setName(colorRequest.getName());
            color.setHexCode(colorRequest.getHexCode());
            color.setProduct(product);
            product.getColorOptions().add(color);
        }

        // Ürün boyut seçeneklerini oluşturup ürüne eklemek için döngü
        for (CreateProductSizeRequest sizeRequest : createProductRequest.getSizeOptions()) {
            Size size = new Size();
            size.setName(sizeRequest.getName());
            size.setProduct(product);
            product.getSizeOptions().add(size);
        }

        // Ürünü veritabanına kaydederek sonucu döndürmek
        return new SuccessDataResult<>(productRepository.save(product), "The product has been successfully added.");
    }


}




