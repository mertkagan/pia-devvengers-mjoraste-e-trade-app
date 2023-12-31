package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.core.utilities.results.Result;
import com.devvengers.mjoraste.service.concretes.AuthService;
import com.devvengers.mjoraste.service.requests.AddressGetRequest;
import com.devvengers.mjoraste.service.requests.AddressRequest;
import com.devvengers.mjoraste.service.requests.LoginRequest;
import com.devvengers.mjoraste.service.requests.RegisterRequest;
import com.devvengers.mjoraste.service.responses.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public DataResult userRegister(@RequestBody RegisterRequest registerRequest, BindingResult bindingResult){
        return authService.userRegister(registerRequest,bindingResult);

    }
    @PostMapping("/login")
    @Validated
   public DataResult<LoginResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest){
       return authService.userLogin(loginRequest);
   }


}
