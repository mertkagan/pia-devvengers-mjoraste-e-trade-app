package com.devvengers.mjoraste.service.requests;

import lombok.Data;

@Data
public class CreateOrderAddressRequest {

    private String city;
    private String town;
    private String fullAddress;
}
