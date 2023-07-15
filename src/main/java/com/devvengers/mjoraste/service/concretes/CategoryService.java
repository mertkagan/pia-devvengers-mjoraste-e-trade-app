package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.ErrorDataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.Brand;
import com.devvengers.mjoraste.entities.Category;
import com.devvengers.mjoraste.repository.CategoryRepository;
import com.devvengers.mjoraste.service.responses.GetAllCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapperService modelMapperService;


    public DataResult<List<GetAllCategoryResponse>> getAllCategories(){

        List<Category> categories = categoryRepository.findAll();

        List<GetAllCategoryResponse> response = categories.stream()
                .map(category -> this.modelMapperService.forResponse().map(category, GetAllCategoryResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(categoryRepository.findAll(),"Data retrieved succesfully");
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }





}
