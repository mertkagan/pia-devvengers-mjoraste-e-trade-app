package com.devvengers.mjoraste.service.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @NotEmpty(message = " Email field cannot be empty.")
    private String email;

    @NotEmpty(message = "Password filed cannot be empty.")
    private String password;
}
