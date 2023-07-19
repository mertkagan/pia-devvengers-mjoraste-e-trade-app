package com.devvengers.mjoraste.service.requests;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private Long userId;

    private Long paymentTypeId;

    private String city;
    private String town;
    private String fullAddress;
}
