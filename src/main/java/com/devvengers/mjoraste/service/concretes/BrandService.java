package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.ErrorDataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.Brand;
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

    public DataResult<Brand> getBrandById(Long brandId){
        Optional<Brand> brandCheck = brandRepository.findById(brandId);

        if (brandCheck.isPresent()){
            return new SuccessDataResult<Brand>(brandCheck.get(), "Data retrieved successfully");
        } else {
            return new ErrorDataResult<>(null, "Brand not found with the given ID.");
        }
    }

}
