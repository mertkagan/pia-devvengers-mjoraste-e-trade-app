package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.Color;
import com.devvengers.mjoraste.entities.Product;
import com.devvengers.mjoraste.repository.ColorRepository;
import com.devvengers.mjoraste.service.requests.CreateColorRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ColorService {

    private ColorRepository colorRepository;

    private ModelMapperService modelMapperService;

    public Optional<Color> findByColorId(Long colorId) {
        return colorRepository.findById(colorId);
    }

    public DataResult createColor(CreateColorRequest createColorRequest){
        Color newColor = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
        colorRepository.save(newColor);

        return new SuccessDataResult(colorRepository.save(newColor),"Color create succesfully.");
    }
}
