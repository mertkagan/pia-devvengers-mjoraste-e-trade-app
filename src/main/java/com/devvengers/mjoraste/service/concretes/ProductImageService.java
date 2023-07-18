package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.results.Result;
import com.devvengers.mjoraste.core.utilities.results.SuccessResult;
import com.devvengers.mjoraste.entities.ProductImage;
import com.devvengers.mjoraste.repository.ProductImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductImageService {

    ProductImageRepository productImageRepository;


    public Result saveProductImage(ProductImage images){
        productImageRepository.save(images);
        return new SuccessResult("Product image added succesfully.");
    }
}
