package com.testproject.miniIMS.service;

import com.testproject.miniIMS.dto.LoginDto;
import com.testproject.miniIMS.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
