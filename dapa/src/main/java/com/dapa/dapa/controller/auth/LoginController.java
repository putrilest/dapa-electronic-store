package com.dapa.dapa.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dapa.dapa.dto.GenericResponse;
import com.dapa.dapa.dto.auth.LoginRequestDto;
import com.dapa.dapa.dto.auth.LoginResponseDto;
import com.dapa.dapa.service.auth.LoginService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto dto) {
        try {
            LoginResponseDto response = loginService.login(dto);
            return ResponseEntity.ok().body(GenericResponse.success(response, "succesfully login!"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.error(e.getReason()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error(e.getMessage()));
        }
    }
}
