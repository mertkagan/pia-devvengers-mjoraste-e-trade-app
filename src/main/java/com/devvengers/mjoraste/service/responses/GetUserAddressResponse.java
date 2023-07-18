package com.devvengers.mjoraste.service.responses;

import lombok.Data;

@Data
public class GetUserAddressResponse {

    private Long userId;
    private String city;

    private String fullAddress;

    private String town;
}
