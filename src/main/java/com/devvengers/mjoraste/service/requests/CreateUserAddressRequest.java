package com.devvengers.mjoraste.service.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserAddressRequest {

    @NotNull
    private Long userId;
    @NotEmpty
    private String city;

    @NotEmpty
    private String town;

    @NotEmpty
    private String fullAddress;
}
