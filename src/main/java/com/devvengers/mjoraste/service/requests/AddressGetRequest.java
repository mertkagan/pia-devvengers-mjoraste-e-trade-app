package com.devvengers.mjoraste.service.requests;

import com.devvengers.mjoraste.entities.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddressGetRequest {
    @NotNull(message = "id alanı boş bırakılamaz.")
    @NotEmpty(message = "id alanı boş bırakılamaz.")
    private Long userId;
}
