package com.devvengers.mjoraste;

import com.devvengers.mjoraste.controller.AuthController;
import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.service.concretes.AboutService;
import com.devvengers.mjoraste.service.concretes.AuthService;
import com.devvengers.mjoraste.service.requests.LoginRequest;
import com.devvengers.mjoraste.service.responses.GetAboutResponse;
import com.devvengers.mjoraste.service.responses.LoginResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AboutTest {

    //@MockBean
    @Autowired
    private AboutService aboutService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(aboutService).isNotNull();
    }

    @Test
    public void loginTest() {
        DataResult<GetAboutResponse> a = aboutService.getAbout();
        if (a == null) assertThat(false);
        else Assertions.assertEquals(true, a.isSuccess());
    }
}