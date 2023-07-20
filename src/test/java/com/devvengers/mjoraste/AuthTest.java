package com.devvengers.mjoraste;

import com.devvengers.mjoraste.controller.AuthController;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.service.concretes.AuthService;
import com.devvengers.mjoraste.service.requests.LoginRequest;
import com.devvengers.mjoraste.service.requests.RegisterRequest;
import com.devvengers.mjoraste.service.responses.LoginResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.validation.BindingResult;

@SpringBootTest
public class AuthTest {

    //@MockBean
    @Autowired
    private AuthService authService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(authService).isNotNull();
    }

    @Test
    public void loginTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("bfatih38@gmail.com5");
        loginRequest.setPassword("123456");
        System.out.println(loginRequest);
        DataResult<LoginResponse> a = authService.userLogin(loginRequest);
        if (a == null) assertThat(false);
        else Assertions.assertEquals(true, a.isSuccess());
    }
}