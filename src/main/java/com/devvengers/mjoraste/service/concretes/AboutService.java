package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.SuccessDataResult;
import com.devvengers.mjoraste.entities.About;
import com.devvengers.mjoraste.entities.Address;
import com.devvengers.mjoraste.repository.AboutRepository;
import com.devvengers.mjoraste.repository.AddressRepository;
import com.devvengers.mjoraste.service.responses.GetAboutResponse;
import com.devvengers.mjoraste.service.responses.GetUserAddressResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AboutService {
    private AboutRepository aboutRepository;
    private ModelMapperService modelMapperService;

    public List<About> getAbout(){

     return aboutRepository.findAll();
    }

}
