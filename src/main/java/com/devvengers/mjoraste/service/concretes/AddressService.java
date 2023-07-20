package com.devvengers.mjoraste.service.concretes;

import com.devvengers.mjoraste.core.utilities.mappers.ModelMapperService;
import com.devvengers.mjoraste.core.utilities.results.*;
import com.devvengers.mjoraste.entities.Address;
import com.devvengers.mjoraste.entities.User;
import com.devvengers.mjoraste.repository.AddressRepository;
import com.devvengers.mjoraste.repository.UserRepository;
import com.devvengers.mjoraste.service.requests.CreateUserAddressRequest;
import com.devvengers.mjoraste.service.responses.GetAllProductByCategoryIdResponse;
import com.devvengers.mjoraste.service.responses.GetUserAddressResponse;
import com.devvengers.mjoraste.service.responses.ProductDetailsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;

    private ModelMapperService modelMapperService;

    private UserRepository userRepository;



    public Result createUserAddress(CreateUserAddressRequest createUserAddressRequest){
        User user = userRepository.findById(createUserAddressRequest.getUserId()).orElse(null);

        if (user != null) {
            Address address = new Address();
            address.setCity(createUserAddressRequest.getCity());
            address.setTown(createUserAddressRequest.getTown());
            address.setFullAddress(createUserAddressRequest.getFullAddress());

            addressRepository.save(address);


            user.setAddress(address);
            userRepository.save(user);

            return new SuccessResult("User address created successfully.");
        } else {
            return new ErrorResult("User Not Found");
        }
    }

    public DataResult<GetUserAddressResponse> getUserAddress(Long userId){
        Optional<Address> address = addressRepository.findByUserId(userId);

        GetUserAddressResponse response = this.modelMapperService.forResponse().map(address, GetUserAddressResponse.class);

        return new SuccessDataResult<GetUserAddressResponse>(response,"Data retrieved succesfully");

    }


}
