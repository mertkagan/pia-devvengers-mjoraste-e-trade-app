package com.devvengers.mjoraste.service.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {
    @NotNull(message = "İsim alanı boş bırakılamaz.")
    @NotEmpty(message = "İsim alanı boş bırakılamaz.")
    private String name;

    @NotNull(message = "Soyad alanı boş bırakılamaz.")
    @NotEmpty(message = "Soyad alanı boş bırakılamaz.")
    private String surName;


    @NotNull(message = "E-mail alanı boş bırakılamaz.")
    @NotEmpty(message = "E-mail alanı boş bırakılamaz.")
    private String email;

    @NotNull(message = "Parola alanı boş bırakılamaz.")
    @NotEmpty(message = "Parola alanı boş bırakılamaz.")
    private String password;

    @NotNull(message = "Telefon alanı boş bırakılamaz.")
    @NotEmpty(message = "Telefon alanı boş bırakılamaz.")
    private String phoneNumber;
}
