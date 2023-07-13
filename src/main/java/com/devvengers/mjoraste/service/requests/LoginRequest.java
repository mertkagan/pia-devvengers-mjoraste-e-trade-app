package com.devvengers.mjoraste.service.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @NotNull(message = "E-mail alanı boş bırakılamaz.")
    @NotEmpty(message = "E-Mail alanı boş bırakılamaz.")
    private String email;

    @NotNull(message = "Parola alanı boş bırakılamaz.")
    @NotEmpty(message = "Parola alanı boş bırakılamaz.")
    private String password;
}
