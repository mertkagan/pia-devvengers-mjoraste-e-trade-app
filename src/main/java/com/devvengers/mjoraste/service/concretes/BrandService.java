package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.ErrorDataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.Brand;
import com.devvengers.mjoraste.entities.Category;
import com.devvengers.mjoraste.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandService {

    private BrandRepository brandRepository;

    private ModelMapperService modelMapperService;

    public Optional<Brand> getBrandById(Long brandId){
       return brandRepository.findById(brandId);
    }

}
