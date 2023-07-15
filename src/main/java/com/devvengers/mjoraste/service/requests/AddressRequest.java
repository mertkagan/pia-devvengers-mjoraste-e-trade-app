package com.devvengers.mjoraste.service.requests;

import com.devvengers.mjoraste.entities.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddressRequest {
    @NotNull(message = "id alanı boş bırakılamaz.")
    @NotEmpty(message = "id alanı boş bırakılamaz.")
    private User id;

    @NotNull(message = "city alanı boş bırakılamaz.")
    @NotEmpty(message = "city alanı boş bırakılamaz.")
    private User city;

    @NotNull(message = "town alanı boş bırakılamaz.")
    @NotEmpty(message = "town alanı boş bırakılamaz.")
    private User town;

    @NotNull(message = "fullAddress alanı boş bırakılamaz.")
    @NotEmpty(message = "fullAddress alanı boş bırakılamaz.")
    private User fullAddress;

    @NotNull(message = "id alanı boş bırakılamaz.")
    @NotEmpty(message = "id alanı boş bırakılamaz.")
    private User user;
}
