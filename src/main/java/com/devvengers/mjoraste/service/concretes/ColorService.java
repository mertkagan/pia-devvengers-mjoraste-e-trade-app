package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.entities.Color;
import com.devvengers.mjoraste.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ColorService {

    private ColorRepository colorRepository;

    public Optional<Color> findByColorId(Long colorId) {
        return colorRepository.findById(colorId);
    }
}
