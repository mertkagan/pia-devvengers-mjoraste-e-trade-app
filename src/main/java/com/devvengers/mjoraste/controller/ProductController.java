package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.entities.Product;
import com.devvengers.mjoraste.service.concretes.ProductService;
import com.devvengers.mjoraste.service.requests.CreateProductRequest;
import com.devvengers.mjoraste.service.responses.GetAllProductByCategoryIdResponse;
import com.devvengers.mjoraste.service.responses.GetAllProductResponse;
import com.devvengers.mjoraste.service.responses.ProductDetailsResponse;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(
            value = "Create Product",
            notes = "Bu API yöntemi, sistemde yeni bir ürün oluşturmak için kullanılır. " +
                    "Ürün adı, fiyatı, açıklaması, boyutu, stok miktarı ve marka ile kategori bilgileri " +
                    "oluşturulacak ürün için zorunlu alanlardır. " +
                    "Ürün oluşturulurken ürünün renk seçenekleri ve resim URL'si de isteğe bağlı olarak eklenilebilir. " +
                    "Bu API, JSON formatında gönderilen isteğe göre yeni bir ürün oluşturur. " +
                    "Oluşturulan ürünün benzersiz bir kimliği veritabanında saklanır ve cevap olarak döndürülür. " +
                    "Oluşturma işlemi başarılı bir şekilde tamamlandığında, HTTP 200 başarılı yanıtı döner ve " +
                    "oluşturulan ürün nesnesini içeren cevap verir. " +
                    "Eğer oluşturma işlemi başarısız olursa, uygun hata mesajı ve HTTP hata kodu ile hata yanıtı döner."
    )
    public DataResult<Product> addProduct(@Valid @RequestBody CreateProductRequest createProductRequest){
        return productService.addProduct(createProductRequest);
    }



}
