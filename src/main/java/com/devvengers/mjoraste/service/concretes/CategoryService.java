package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.ErrorDataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.Brand;
import com.devvengers.mjoraste.entities.Category;
import com.devvengers.mjoraste.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapperService modelMapperService;

    public DataResult<Category> getCategoryById(Long categoryId){
        Optional<Category> categoryCheck = categoryRepository.findById(categoryId);

        if (categoryCheck.isPresent()){
            return new SuccessDataResult<Category>(categoryCheck.get(), "Data retrieved successfully");
        } else {
            return new ErrorDataResult<>(null, "Category not found with the given ID.");
        }
    }




}
