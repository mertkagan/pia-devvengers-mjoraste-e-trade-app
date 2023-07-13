package com.devvengers.mjoraste.service.responses;

import lombok.Data;

@Data
public class LoginResponse {

    private String id;

    private String name;

    private String surName;

    private String email;

    private String phoneNumber;
}
