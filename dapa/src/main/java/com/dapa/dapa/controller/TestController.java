package com.dapa.dapa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/test")
@Tag(name = "Test")
public class TestController {
    @GetMapping("")
    @SecurityRequirement(name = "Bearer Authentication")
    public String test() {
        return "hello";
    }
}
