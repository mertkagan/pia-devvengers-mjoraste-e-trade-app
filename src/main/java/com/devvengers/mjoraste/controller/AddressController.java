package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.Result;
import com.devvengers.mjoraste.service.concretes.AddressService;
import com.devvengers.mjoraste.service.concretes.UserService;
import com.devvengers.mjoraste.service.requests.CreateUserAddressRequest;
import com.devvengers.mjoraste.service.responses.GetUserAddressResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;

    @GetMapping
    public DataResult<GetUserAddressResponse> getUserAddress(@RequestParam Long userId) {
        return addressService.getUserAddress(userId);
    }


    @PostMapping
    @Validated
    public Result createUserAddress(@Valid @RequestBody CreateUserAddressRequest createUserAddressRequest) {
        return addressService.createUserAddress(createUserAddressRequest);
    }
}
