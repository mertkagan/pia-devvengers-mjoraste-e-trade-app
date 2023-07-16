package com.devvengers.mjoraste.service.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {


    private String email;


    private String password;
}
