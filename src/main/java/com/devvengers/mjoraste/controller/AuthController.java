package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.service.concretes.AuthService;
import com.devvengers.mjoraste.service.requests.LoginRequest;
import com.devvengers.mjoraste.service.requests.RegisterRequest;
import com.devvengers.mjoraste.service.responses.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping
    public String userRegister(@RequestBody RegisterRequest registerRequest, BindingResult bindingResult){
        return authService.userRegister(registerRequest,bindingResult);
    }

    @GetMapping("/login")
   public LoginResponse userLogin(@RequestBody LoginRequest loginRequest, BindingResult bindingResult){
       return authService.userLogin(loginRequest, bindingResult);
   }

}
