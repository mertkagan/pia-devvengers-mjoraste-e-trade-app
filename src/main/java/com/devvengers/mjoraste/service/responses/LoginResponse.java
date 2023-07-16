package com.devvengers.mjoraste.service.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {



    private Long id;

    private String name;

    private String surName;

    private String email;

    private String phoneNumber;
}
