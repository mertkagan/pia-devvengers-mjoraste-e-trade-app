package com.devvengers.mjoraste.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperManager implements ModelMapperService {

    private ModelMapper modelMapper;


    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)//belirsizlik olduğunda onu ignore et.
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        //GEVŞEK MAPLAMA : responseda sadece olanı mapla

        return this.modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        //herşeyi mapla

        return this.modelMapper;
    }
}
