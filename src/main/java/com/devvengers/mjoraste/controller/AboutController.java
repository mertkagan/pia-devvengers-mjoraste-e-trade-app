package com.devvengers.mjoraste.controller;

import com.devvengers.mjoraste.core.utilities.results.DataResult;
import com.devvengers.mjoraste.entities.About;
import com.devvengers.mjoraste.service.concretes.AboutService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.devvengers.mjoraste.service.responses.GetAboutResponse;

import java.util.List;

@RestController
@RequestMapping("/api/about")
@AllArgsConstructor
public class AboutController {
    private AboutService aboutService;
    @GetMapping()
    public List<About> getAbout() {
        return aboutService.getAbout();
    }
}
