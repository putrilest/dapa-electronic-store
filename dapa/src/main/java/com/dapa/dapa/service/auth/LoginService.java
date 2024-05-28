package com.dapa.dapa.service.auth;

import com.dapa.dapa.dto.auth.LoginRequestDto;
import com.dapa.dapa.dto.auth.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto dto);
}
