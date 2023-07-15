package com.devvengers.mjoraste.service.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponse {

    public AddressResponse(){}

    private String city;

    private Long id;

    private String town;

    private String fullAddress;
}
