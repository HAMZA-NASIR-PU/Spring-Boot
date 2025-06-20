package com.security.test.services;

import com.security.test.dtos.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
