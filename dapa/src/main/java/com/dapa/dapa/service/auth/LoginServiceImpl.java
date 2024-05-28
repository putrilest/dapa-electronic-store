package com.dapa.dapa.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dapa.dapa.dto.auth.LoginRequestDto;
import com.dapa.dapa.dto.auth.LoginResponseDto;
import com.dapa.dapa.entity.Users;
import com.dapa.dapa.repository.UserRepository;
import com.dapa.dapa.utils.JwtUtil;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        Users user = userRepository.findByUsername(dto.getUsername());
        if (user != null) {
            boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());
            if (isMatch) {
                LoginResponseDto response = new LoginResponseDto();
                response.setUsername(user.getUsername());
                response.setRole(user.getRoles().getRoleName());
                response.setToken(jwtUtil.generateToken(user));
                return response;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password");
    }

}
