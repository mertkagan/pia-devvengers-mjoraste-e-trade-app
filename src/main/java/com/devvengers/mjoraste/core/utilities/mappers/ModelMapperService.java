package com.devvengers.mjoraste.core.utilities.mappers;

import com.devvengers.mjoraste.entities.User;
import com.devvengers.mjoraste.service.responses.LoginResponse;
import org.modelmapper.ModelMapper;

public interface ModelMapperService{

    ModelMapper forResponse();
    ModelMapper forRequest();


}
